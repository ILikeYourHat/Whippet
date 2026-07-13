package io.github.ilikeyourhat.whippet.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute

@Composable
fun BottomNavigationBar(
    currentRoute: NavDestination?,
    onItemClick: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = BottomNavigationScreen.entries

    val currentBottomNavScreen = when {
        currentRoute?.hasRoute<Screen.NotesList>() == true -> BottomNavigationScreen.NOTES_LIST
        currentRoute?.hasRoute<Screen.Settings>() == true -> BottomNavigationScreen.SETTINGS
        else -> null
    }

    val isCurrentScreen: (BottomNavigationScreen) -> Boolean = { it == currentBottomNavScreen }

    NavigationBar(
        modifier = modifier.fillMaxWidth()
    ) {
        items.forEach { navigationItem ->
            NavigationBarItem(
                selected = isCurrentScreen(navigationItem),
                onClick = { onItemClick(navigationItem.screen()) },
                icon = {
                    Icon(
                        imageVector = if (isCurrentScreen(navigationItem)) {
                            navigationItem.selectedIcon()
                        } else {
                            navigationItem.unselectedIcon()
                        },
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = navigationItem.localizedName(),
                        style = if (isCurrentScreen(navigationItem)) {
                            MaterialTheme.typography.labelLarge
                        } else {
                            MaterialTheme.typography.labelMedium
                        },
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
            )
        }
    }
}
