package io.github.ilikeyourhat.whippet.ui

import androidx.compose.ui.graphics.Color


data class Colors(
    val isDark: Boolean,
    val background: Color,
    val text: Color,
    val icon: Color,
)

val LightThemeColors = Colors(
    isDark = false,
    background = Palette.white100,
    text = Palette.black100,
    icon = Palette.black100,
)

val DarkThemeColors = Colors(
    isDark = true,
    background = Palette.black100,
    text = Palette.white100,
    icon = Palette.white100,
)

private object Palette {
    val black100 = Color(0xFF000000)
    val white100 = Color(0xFFFFFFFF)
}
