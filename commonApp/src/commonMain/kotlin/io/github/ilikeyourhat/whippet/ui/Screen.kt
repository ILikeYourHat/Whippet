package io.github.ilikeyourhat.whippet.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CalendarMonth
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

    data object History : BottomNavigationScreen(
        route = "history",
        selectedIcon = Icons.Filled.CalendarMonth,
        unSelectedIcon = Icons.Outlined.CalendarMonth
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
