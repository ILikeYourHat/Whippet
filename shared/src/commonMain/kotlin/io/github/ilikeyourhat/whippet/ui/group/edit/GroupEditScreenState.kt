package io.github.ilikeyourhat.whippet.ui.group.edit

sealed class GroupEditScreenState(
    open val isNew: Boolean
) {

    data class Loading(
        override val isNew: Boolean
    ) : GroupEditScreenState(isNew)

    data class Content(
        override val isNew: Boolean,
        val title: String = ""
    ) : GroupEditScreenState(isNew)
}
