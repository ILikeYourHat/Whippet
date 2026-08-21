package io.github.ilikeyourhat.whippet.ui.group.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metrox.viewmodel.ManualViewModelAssistedFactory
import dev.zacsweers.metrox.viewmodel.ManualViewModelAssistedFactoryKey
import io.github.ilikeyourhat.whippet.db.notes.NoteEntity
import io.github.ilikeyourhat.whippet.db.notes.NotesDao
import io.github.ilikeyourhat.whippet.ui.navigation.Navigator
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.uuid.Uuid

@AssistedInject
class GroupEditViewModel(
    @Assisted val groupId: Uuid?,
    @Assisted val parentGroupId: Uuid?,
    val notesDao: NotesDao,
    val navigator: Navigator
) : ViewModel() {

    @AssistedFactory
    @ManualViewModelAssistedFactoryKey
    @ContributesIntoMap(AppScope::class)
    interface Factory : ManualViewModelAssistedFactory {
        fun create(groupId: Uuid?, parentGroupId: Uuid?): GroupEditViewModel
    }

    val uiState: StateFlow<GroupEditScreenState>
        field = MutableStateFlow<GroupEditScreenState>(GroupEditScreenState.Loading(groupId == null))

    private val originalGroup = viewModelScope.async {
        val note = if (groupId != null) {
            notesDao.getById(groupId)
        } else null

        note ?: NoteEntity(
            id = groupId ?: Uuid.random(),
            groupId = parentGroupId,
            isGroup = true
        )
    }

    init {
        viewModelScope.launch {
            val group = originalGroup.await()
            uiState.value = GroupEditScreenState.Content(
                isNew = groupId == null,
                title = group.title.orEmpty(),
            )
        }
    }

    fun onTitleChange(title: String) {
        val uiStateValue = uiState.value as? GroupEditScreenState.Content ?: return
        uiState.value = uiStateValue.copy(title = title)
    }

    fun onBackClick() {
        viewModelScope.launch {
            navigator.goBack()
        }
    }

    fun onSaveClick() {
        viewModelScope.launch {
            val uiStateValue = uiState.value as? GroupEditScreenState.Content ?: return@launch
            val entity = originalGroup.await().copy(
                title = uiStateValue.title
            )
            notesDao.insertOrReplace(entity)
            navigator.goBack()
        }
    }
}
