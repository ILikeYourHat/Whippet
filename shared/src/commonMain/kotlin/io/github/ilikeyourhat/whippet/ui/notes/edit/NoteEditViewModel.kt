package io.github.ilikeyourhat.whippet.ui.notes.edit

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
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlin.uuid.Uuid

@AssistedInject
class NoteEditViewModel(
    @Assisted val noteId: Uuid?,
    @Assisted val groupId: Uuid?,
    val notesDao: NotesDao,
    val navigator: Navigator
) : ViewModel() {

    @AssistedFactory
    @ManualViewModelAssistedFactoryKey
    @ContributesIntoMap(AppScope::class)
    interface Factory : ManualViewModelAssistedFactory {
        fun create(noteId: Uuid?, groupId: Uuid?): NoteEditViewModel
    }

    val originalNote = flow {
        val note = if (noteId != null) {
            notesDao.getById(noteId)
        } else null

        emit(
            note ?: NoteEntity(
                id = noteId ?: Uuid.random(),
                groupId = groupId
            )
        )
    }

    val uiState = MutableStateFlow(
        NoteEditScreenState()
    )

    init {
        viewModelScope.launch {
            val note = originalNote.last()
            uiState.value = NoteEditScreenState(
                title = note.title.orEmpty(),
                textContent = note.value.orEmpty(),
            )
        }
    }

    fun onTitleChange(title: String) {
        uiState.value = uiState.value.copy(title = title)
    }

    fun onTextContentChange(textContent: String) {
        uiState.value = uiState.value.copy(textContent = textContent)
    }

    fun onBackClick() {
        viewModelScope.launch {
            navigator.goBack()
        }
    }

    fun onSaveClick() {
        viewModelScope.launch {
            val entity = originalNote.last().copy(
                title = uiState.value.title,
                value = uiState.value.textContent
            )
            notesDao.insertOrReplace(entity)
            navigator.goBack()
        }
    }
}
