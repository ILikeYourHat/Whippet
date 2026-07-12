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
import kotlinx.coroutines.launch
import kotlin.uuid.Uuid

@AssistedInject
class GroupEditViewModel(
    @Assisted val groupId: Uuid?,
    val notesDao: NotesDao,
    val navigator: Navigator
) : ViewModel() {

    @AssistedFactory
    @ManualViewModelAssistedFactoryKey
    @ContributesIntoMap(AppScope::class)
    interface Factory : ManualViewModelAssistedFactory {
        fun create(groupId: Uuid?): GroupEditViewModel
    }

    init {
        viewModelScope.launch {
            if (groupId != null) {
                val event = notesDao.getById(groupId)
                uiState.value = GroupEditScreenState(
                    title = event?.title.orEmpty(),
                )
            }
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
            val entity = NoteEntity(
                id = groupId ?: Uuid.random(),
                title = uiState.value.title,
                isGroup = true
            )
            notesDao.insertOrReplace(entity)
            navigator.goBack()
        }
    }
}
