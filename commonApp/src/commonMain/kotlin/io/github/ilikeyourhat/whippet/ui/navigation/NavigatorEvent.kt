package io.github.ilikeyourhat.whippet.ui.navigation

import io.github.ilikeyourhat.whippet.ui.Screen

sealed class NavigatorEvent {
    data class Destination(val screen: Screen): NavigatorEvent()
    data object BackInvocation: NavigatorEvent()
}