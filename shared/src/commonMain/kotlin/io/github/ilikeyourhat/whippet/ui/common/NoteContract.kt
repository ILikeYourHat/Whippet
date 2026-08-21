package io.github.ilikeyourhat.whippet.ui.common

import kotlin.uuid.Uuid

interface NoteContract {
    fun onItemClick(id: Uuid)
    fun onItemEdit(id: Uuid)
    fun onItemDelete(id: Uuid)

    companion object Empty : NoteContract {
        override fun onItemClick(id: Uuid) = Unit
        override fun onItemEdit(id: Uuid) = Unit
        override fun onItemDelete(id: Uuid) = Unit
    }
}
