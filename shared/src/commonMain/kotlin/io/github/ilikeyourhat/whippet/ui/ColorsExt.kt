package io.github.ilikeyourhat.whippet.ui

import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun Colors.navigationBarItem() = NavigationBarItemDefaults.colors(
    selectedIconColor = WhippetTheme.colors.surface,
    selectedTextColor = WhippetTheme.colors.surface,
    indicatorColor = WhippetTheme.colors.fab,
    unselectedIconColor = WhippetTheme.colors.icon,
    unselectedTextColor = WhippetTheme.colors.text,
    disabledIconColor = Color.Unspecified,
)

@Composable
fun Colors.topAppBar() = TopAppBarColors(
    containerColor = WhippetTheme.colors.surface,
    scrolledContainerColor = WhippetTheme.colors.surface,
    navigationIconContentColor = WhippetTheme.colors.icon,
    titleContentColor = WhippetTheme.colors.text,
    actionIconContentColor = WhippetTheme.colors.icon,
    subtitleContentColor = WhippetTheme.colors.text,
)
