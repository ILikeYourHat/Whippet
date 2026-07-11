package io.github.ilikeyourhat.whippet

import androidx.compose.ui.window.ComposeUIViewController
import dev.zacsweers.metro.createGraph
import io.github.ilikeyourhat.whippet.di.IosAppGraph
import platform.UIKit.UIViewController

fun create(): UIViewController {
    val appGraph = createGraph<IosAppGraph>()
    return ComposeUIViewController {
        App(appGraph)
    }
}
