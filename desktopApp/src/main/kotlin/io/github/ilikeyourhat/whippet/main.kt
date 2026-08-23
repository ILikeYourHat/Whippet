package io.github.ilikeyourhat.io.github.ilikeyourhat.whippet

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.zacsweers.metro.createGraph
import io.github.ilikeyourhat.io.github.ilikeyourhat.whippet.di.JvmAppGraph
import io.github.ilikeyourhat.whippet.App
import io.github.ilikeyourhat.whippet.Res
import io.github.ilikeyourhat.whippet.app_icon
import org.jetbrains.compose.resources.painterResource
import java.awt.Dimension

fun main() = application {
    val appGraph = createGraph<JvmAppGraph>()
    Window(
        icon = painterResource(Res.drawable.app_icon),
        onCloseRequest = ::exitApplication,
        title = "Whippet",
    ) {
        window.minimumSize = Dimension(400, 400)
        App(appGraph)
    }
}