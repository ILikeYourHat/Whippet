package io.github.ilikeyourhat.whippet.ui.notes.edit

sealed class NoteEditScreenState(
    open val isNew: Boolean
) {

    data class Loading(
        override val isNew: Boolean
    ) : NoteEditScreenState(isNew)

    data class Content(
        override val isNew: Boolean,
        val title: String = "",
        val textContent: String = ""
    ) : NoteEditScreenState(isNew)
}
