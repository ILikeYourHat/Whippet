package io.github.ilikeyourhat.whippet.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.tooling.preview.PreviewWrapperProvider

val LocalColors = compositionLocalOf<Colors> {
    error("WhippetTheme must be part of the call hierarchy to provide colors")
}

object WhippetTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
}

@Composable
fun WhippetTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides if (isSystemInDarkTheme()) DarkThemeColors else LightThemeColors
    ) {
        content()
    }
}

class WhippetThemePreviewWrapper : PreviewWrapperProvider {
    @Composable
    override fun Wrap(content: @Composable () -> Unit) {
        WhippetTheme {
            content()
        }
    }
}
