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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
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

    val originalGroup = flow {
        val note = if (groupId != null) {
            notesDao.getById(groupId)
        } else null

        emit(
            note ?: NoteEntity(
                id = groupId ?: Uuid.random(),
                groupId = parentGroupId,
                isGroup = true
            )
        )
    }

    init {
        viewModelScope.launch {
            val group = originalGroup.single()
            uiState.value = GroupEditScreenState(
                title = group.title.orEmpty(),
            )
        }
    }

    val uiState = MutableStateFlow(
        GroupEditScreenState()
    )

    fun onTitleChange(title: String) {
        uiState.value = uiState.value.copy(title = title)
    }

    fun onBackClick() {
        viewModelScope.launch {
            navigator.goBack()
        }
    }

    fun onSaveClick() {
        viewModelScope.launch {
            val entity = originalGroup.single().copy(
                title = uiState.value.title
            )
            notesDao.insertOrReplace(entity)
            navigator.goBack()
        }
    }
}
