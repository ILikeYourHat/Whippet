package io.github.ilikeyourhat.whippet

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.zacsweers.metro.createGraph
import io.github.ilikeyourhat.whippet.di.JvmAppGraph

fun main() = application {
    val appGraph = createGraph<JvmAppGraph>()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Whippet",
    ) {
        App(appGraph)
    }
}