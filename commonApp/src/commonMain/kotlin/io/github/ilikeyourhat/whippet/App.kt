package io.github.ilikeyourhat.whippet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.zacsweers.metrox.viewmodel.LocalMetroViewModelFactory
import io.github.ilikeyourhat.whippet.di.AppGraph
import io.github.ilikeyourhat.whippet.ui.navigation.Navigator
import io.github.ilikeyourhat.whippet.ui.BottomNavigationBar
import io.github.ilikeyourhat.whippet.ui.NoteAddScreen
import io.github.ilikeyourhat.whippet.ui.NoteListScreen
import io.github.ilikeyourhat.whippet.ui.Screen
import io.github.ilikeyourhat.whippet.ui.calendar.CalendarScreen

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

    LaunchedEffect("NavigationEvents") {
        navigator.route.collect { screen ->
            navController.navigate(screen.route)
        }
    }

    val currentRoute = backStackEntry?.destination?.route ?: Screen.Home.route
    MaterialTheme {
        Column(
            modifier = modifier.fillMaxSize()
                .safeContentPadding()
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier
                    .weight(1f)
            ) {
                composable(route = Screen.Home.route) {
                    CalendarScreen(
                        modifier = Modifier.fillMaxSize()
                    )
                }
                composable(route = Screen.Stats.route) {
                    Text("hello2")
                }
                composable(route = Screen.Notes.route) {
                    NoteListScreen(
                        navController = navController,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                composable(route = Screen.Settings.route) {
                    Text("hello4")
                }
                composable(route = Screen.NotesAdd.route) {
                    NoteAddScreen(
                        navController = navController,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                composable(route = Screen.AddCalendarEvent.route) {
                    NoteAddScreen(
                        navController = navController,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            BottomNavigationBar(
                currentRoute = currentRoute,
                onItemClick = { navigationItem ->
                    navController.navigate(navigationItem.route) {
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
