package io.github.ilikeyourhat.whippet.ui.navigation

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import io.github.ilikeyourhat.whippet.ui.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@SingleIn(AppScope::class)
@Inject
class Navigator {

    private val _route = MutableSharedFlow<NavigatorEvent>()
    val route: SharedFlow<NavigatorEvent> = _route

    suspend fun navigateTo(screen: Screen) {
        _route.emit(NavigatorEvent.Destination(screen))
    }

    suspend fun goBack() {
        _route.emit(NavigatorEvent.BackInvocation)
    }
}
