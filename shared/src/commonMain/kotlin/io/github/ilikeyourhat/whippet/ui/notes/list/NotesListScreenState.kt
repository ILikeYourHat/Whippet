package io.github.ilikeyourhat.whippet.ui.notes.list

import io.github.ilikeyourhat.whippet.db.notes.NoteEntity

sealed class NotesListScreenState {

    abstract val isRoot: Boolean

    data class Loading(
        override val isRoot: Boolean
    ) : NotesListScreenState()

    data class Content(
        override val isRoot: Boolean,
        val notes: List<NoteEntity>
    ) : NotesListScreenState()
}
