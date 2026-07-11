package io.github.ilikeyourhat.whippet.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.automirrored.outlined.Note
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    abstract fun route(): String

    data object Home : Screen(), BottomNavigationScreen {
        override fun route() = "home"
        override fun selectedIcon() = Icons.Filled.Home
        override fun unselectedIcon() = Icons.Outlined.Home
    }

    data object Stats : Screen(), BottomNavigationScreen {
        override fun route() = "stats"
        override fun selectedIcon() = Icons.Filled.PieChart
        override fun unselectedIcon() = Icons.Outlined.PieChart
    }

    @Serializable
    data class NotesList(
        val groupId: Long? = null
    ) : Screen(), BottomNavigationScreen {
        override fun route() = "noteList"
        override fun selectedIcon() = Icons.AutoMirrored.Filled.Note
        override fun unselectedIcon() = Icons.AutoMirrored.Outlined.Note
    }

    data object NotesAdd : Screen() {
        override fun route() = "addNote"
    }

    @Serializable
    data class AddCalendarEvent(
        val id: Long? = null
    ) : Screen() {
        override fun route() = "addCalendarEvent"
    }


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
