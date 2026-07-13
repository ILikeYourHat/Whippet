package io.github.ilikeyourhat.whippet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.zacsweers.metrox.viewmodel.LocalMetroViewModelFactory
import io.github.ilikeyourhat.whippet.di.AppGraph
import io.github.ilikeyourhat.whippet.ui.navigation.Navigator
import io.github.ilikeyourhat.whippet.ui.BottomNavigationBar
import io.github.ilikeyourhat.whippet.ui.Screen
import io.github.ilikeyourhat.whippet.ui.group.edit.GroupEditScreen
import io.github.ilikeyourhat.whippet.ui.navigation.NavigatorEvent
import io.github.ilikeyourhat.whippet.ui.navigation.UuidNavType
import io.github.ilikeyourhat.whippet.ui.notes.edit.NoteEditScreen
import io.github.ilikeyourhat.whippet.ui.notes.list.NotesListScreen
import io.github.ilikeyourhat.whippet.ui.settings.SettingsScreen
import kotlin.reflect.typeOf
import kotlin.uuid.Uuid

@Composable
fun App(
    appGraph: AppGraph,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalMetroViewModelFactory provides appGraph.metroViewModelFactory) {
        App(
            navigator = appGraph.navigator,
            modifier = modifier
        )
    }
}

@Composable
fun App(
    navigator: Navigator,
    modifier: Modifier = Modifier
) {
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val uriHandler = LocalUriHandler.current

    LaunchedEffect("NavigationEvents") {
        navigator.route.collect { event ->
            when (event) {
                is NavigatorEvent.Destination -> navController.navigate(event.screen)
                is NavigatorEvent.BackInvocation -> navController.popBackStack()
                is NavigatorEvent.OpenLink -> uriHandler.openUri(event.link)
            }
        }
    }
    MaterialTheme {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.NotesList(),
                modifier = Modifier
                    .weight(1f)
            ) {
                composable<Screen.NotesList>(
                    typeMap = mapOf(
                        typeOf<Uuid?>() to UuidNavType
                    )
                ) {
                    val route = it.toRoute<Screen.NotesList>()
                    NotesListScreen(
                        groupId = route.groupId,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                composable<Screen.Settings> {
                    SettingsScreen(
                        modifier = Modifier.fillMaxSize()
                    )
                }
                composable<Screen.NotesAdd> {
                    NoteEditScreen(
                        noteId = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                composable<Screen.GroupAdd> {
                    GroupEditScreen(
                        groupId = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            BottomNavigationBar(
                currentRoute = backStackEntry?.destination,
                onItemClick = { navigationItem ->
                    navController.navigate(navigationItem) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            )
        }
    }
}