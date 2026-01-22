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

sealed class Screen(
    val route: String
) {

    data object Home : BottomNavigationScreen(
        route = "home",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    )

    data object Stats : BottomNavigationScreen(
        route = "stats",
        selectedIcon = Icons.Filled.PieChart,
        unSelectedIcon = Icons.Outlined.PieChart
    )

    data object Notes : BottomNavigationScreen(
        route = "notes",
        selectedIcon = Icons.AutoMirrored.Filled.Note,
        unSelectedIcon = Icons.AutoMirrored.Outlined.Note
    )

    data object NotesAdd : Screen(
        route = "notesAdd"
    )

    data object AddCalendarEvent : Screen(
        route = "addCalendarEvent"
    )

    data object Settings : BottomNavigationScreen(
        route = "settings",
        selectedIcon = Icons.Filled.Settings,
        unSelectedIcon = Icons.Outlined.Settings
    )
}

sealed class BottomNavigationScreen(
    route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
) : Screen(route)
