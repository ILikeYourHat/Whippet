package io.github.ilikeyourhat.whippet.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.automirrored.outlined.Note
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
sealed class Screen {
    abstract fun route(): String

    @Serializable
    data class NotesList(
        val groupId: Uuid? = null
    ) : Screen(), BottomNavigationScreen {
        override fun route() = "noteList"
        override fun selectedIcon() = Icons.AutoMirrored.Filled.Note
        override fun unselectedIcon() = Icons.AutoMirrored.Outlined.Note
    }

    @Serializable
    data object NotesAdd : Screen() {
        override fun route() = "noteAdd"
    }

    @Serializable
    data object GroupAdd : Screen() {
        override fun route() = "groupAdd"
    }

    @Serializable
    data object Settings : Screen(), BottomNavigationScreen {
        override fun route() = "settings"
        override fun selectedIcon() = Icons.Filled.Settings
        override fun unselectedIcon() = Icons.Outlined.Settings
    }
}


interface BottomNavigationScreen{
    fun selectedIcon(): ImageVector
    fun unselectedIcon(): ImageVector
}
