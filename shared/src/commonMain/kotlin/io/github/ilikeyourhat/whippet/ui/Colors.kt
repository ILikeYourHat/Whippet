package io.github.ilikeyourhat.whippet.ui

import androidx.compose.ui.graphics.Color


data class Colors(
    val isDark: Boolean,
    val background: Color,
    val surface: Color,
    val text: Color,
    val icon: Color,
    val fab: Color,
    val button: Color,
)

val LightThemeColors = Colors(
    isDark = false,
    background = Palette.white_90,
    surface = Palette.white_70,
    text = Palette.black_80,
    icon = Palette.black_80,
    fab = Palette.brand_cyan,
    button = Palette.brand_cyan,
)

val DarkThemeColors = Colors(
    isDark = true,
    background = Palette.black_90,
    surface = Palette.black_80,
    text = Palette.white_70,
    icon = Palette.white_70,
    fab = Palette.brand_purple,
    button = Palette.brand_purple,
)

private object Palette {
    // Neutral
    val white_90 = Color(0xFFF9F9FF)
    val white_70 = Color(0xFFE3E3EC)

    val black_90 = Color(0xFF101117)
    val black_80 = Color(0xFF191A20)

    // Brand
    val brand_cyan = Color(0xFF10BDC4)
    val brand_blue = Color(0xFF167FC9)
    val brand_indigo = Color(0xFF2934D0)
    val brand_purple = Color(0xFF3510C7)

    // Semantic
    val error = Color(0xFFBA1A1A)
    val error_light = Color(0xFFFFB4AB)
    val error_dark = Color(0xFF93000A)
}
