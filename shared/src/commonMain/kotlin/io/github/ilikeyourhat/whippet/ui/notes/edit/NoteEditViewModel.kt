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
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    val uiState: StateFlow<NoteEditScreenState>
        field = MutableStateFlow<NoteEditScreenState>(NoteEditScreenState.Loading(noteId == null))

    private val originalNote = viewModelScope.async {
        val note = if (noteId != null) {
            notesDao.getById(noteId)
        } else null

        note ?: NoteEntity(
            id = noteId ?: Uuid.random(),
            groupId = groupId
        )
    }

    init {
        viewModelScope.launch {
            val note = originalNote.await()
            uiState.value = NoteEditScreenState.Content(
                isNew = noteId == null,
                title = note.title.orEmpty(),
                textContent = note.value.orEmpty(),
            )
        }
    }

    fun onTitleChange(title: String) {
        val uiStateValue = uiState.value as? NoteEditScreenState.Content ?: return
        uiState.value = uiStateValue.copy(title = title)
    }

    fun onTextContentChange(textContent: String) {
        val uiStateValue = uiState.value as? NoteEditScreenState.Content ?: return
        uiState.value = uiStateValue.copy(textContent = textContent)
    }

    fun onBackClick() {
        viewModelScope.launch {
            navigator.goBack()
        }
    }

    fun onSaveClick() {
        viewModelScope.launch {
            val uiStateValue = uiState.value as? NoteEditScreenState.Content ?: return@launch
            val entity = originalNote.await().copy(
                title = uiStateValue.title,
                value = uiStateValue.textContent
            )
            notesDao.insertOrReplace(entity)
            navigator.goBack()
        }
    }
}
