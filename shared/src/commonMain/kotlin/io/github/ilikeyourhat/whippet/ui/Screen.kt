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
    @Serializable
    data class NotesList(
        val groupId: Uuid? = null
    ) : Screen()

    @Serializable
    data object NotesAdd : Screen()

    @Serializable
    data object GroupAdd : Screen()

    @Serializable
    data object Settings : Screen()
}

enum class BottomNavigationScreen {
    NOTES_LIST {
        override fun localizedName() = "My notes"
        override fun selectedIcon() = Icons.AutoMirrored.Filled.Note
        override fun unselectedIcon() = Icons.AutoMirrored.Outlined.Note
        override fun screen() = Screen.NotesList()
    },
    SETTINGS {
        override fun localizedName() = "Settings"
        override fun selectedIcon() = Icons.Filled.Settings
        override fun unselectedIcon() = Icons.Outlined.Settings
        override fun screen() = Screen.Settings
    };

    abstract fun localizedName(): String
    abstract fun selectedIcon(): ImageVector
    abstract fun unselectedIcon(): ImageVector
    abstract fun screen(): Screen
}
