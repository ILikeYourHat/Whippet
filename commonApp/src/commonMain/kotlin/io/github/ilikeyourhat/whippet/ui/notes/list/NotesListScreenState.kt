package io.github.ilikeyourhat.whippet.ui.notes.list

import io.github.ilikeyourhat.whippet.db.notes.NoteEntity

sealed class NotesListScreenState {

    data object Loading : NotesListScreenState()

    data class Content(
        val notes: List<NoteEntity>
    ) : NotesListScreenState()
}
