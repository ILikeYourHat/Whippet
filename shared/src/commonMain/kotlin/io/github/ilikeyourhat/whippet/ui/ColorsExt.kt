@file:Suppress("UnusedReceiverParameter")

package io.github.ilikeyourhat.whippet.ui

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun Colors.navigationBarItem() = NavigationBarItemDefaults.colors(
    selectedIconColor = WhippetTheme.colors.surface,
    selectedTextColor = WhippetTheme.colors.surface,
    indicatorColor = WhippetTheme.colors.fab,
    unselectedIconColor = WhippetTheme.colors.icon,
    unselectedTextColor = WhippetTheme.colors.text,
    disabledIconColor = Color.Unspecified,
)

@Composable
fun Colors.topAppBar() = TopAppBarColors(
    containerColor = WhippetTheme.colors.surface,
    scrolledContainerColor = WhippetTheme.colors.surface,
    navigationIconContentColor = WhippetTheme.colors.icon,
    titleContentColor = WhippetTheme.colors.text,
    actionIconContentColor = WhippetTheme.colors.icon,
    subtitleContentColor = WhippetTheme.colors.text,
)

@Composable
fun Colors.button() = ButtonColors(
    containerColor = WhippetTheme.colors.button,
    contentColor = WhippetTheme.colors.text,
    disabledContentColor = Color.Unspecified,
    disabledContainerColor = Color.Unspecified,
)

@Composable
fun Colors.textField() = TextFieldColors(
    focusedTextColor = WhippetTheme.colors.text,
    unfocusedTextColor = WhippetTheme.colors.text,
    disabledTextColor = WhippetTheme.colors.text,
    errorTextColor = WhippetTheme.colors.text,
    focusedContainerColor = WhippetTheme.colors.surface,
    unfocusedContainerColor = WhippetTheme.colors.surface,
    disabledContainerColor = WhippetTheme.colors.surface,
    errorContainerColor = WhippetTheme.colors.surface,
    cursorColor = WhippetTheme.colors.text,
    errorCursorColor = WhippetTheme.colors.text,
    textSelectionColors = TextSelectionColors(
        handleColor = WhippetTheme.colors.button,
        backgroundColor = WhippetTheme.colors.button,
    ),
    focusedIndicatorColor = WhippetTheme.colors.button,
    unfocusedIndicatorColor = WhippetTheme.colors.button,
    disabledIndicatorColor = WhippetTheme.colors.button,
    errorIndicatorColor = WhippetTheme.colors.button,
    focusedLeadingIconColor = WhippetTheme.colors.icon,
    unfocusedLeadingIconColor = WhippetTheme.colors.icon,
    disabledLeadingIconColor = WhippetTheme.colors.icon,
    errorLeadingIconColor = WhippetTheme.colors.icon,
    focusedTrailingIconColor = WhippetTheme.colors.icon,
    unfocusedTrailingIconColor = WhippetTheme.colors.icon,
    disabledTrailingIconColor = WhippetTheme.colors.icon,
    errorTrailingIconColor = WhippetTheme.colors.icon,
    focusedLabelColor = WhippetTheme.colors.text,
    unfocusedLabelColor = WhippetTheme.colors.text,
    disabledLabelColor = WhippetTheme.colors.text,
    errorLabelColor = WhippetTheme.colors.text,
    focusedPlaceholderColor = Color.Unspecified,
    unfocusedPlaceholderColor = Color.Unspecified,
    disabledPlaceholderColor = Color.Unspecified,
    errorPlaceholderColor = Color.Unspecified,
    focusedSupportingTextColor = Color.Unspecified,
    unfocusedSupportingTextColor = Color.Unspecified,
    disabledSupportingTextColor = Color.Unspecified,
    errorSupportingTextColor = Color.Unspecified,
    focusedPrefixColor = Color.Unspecified,
    unfocusedPrefixColor = Color.Unspecified,
    disabledPrefixColor = Color.Unspecified,
    errorPrefixColor = Color.Unspecified,
    focusedSuffixColor = Color.Unspecified,
    unfocusedSuffixColor = Color.Unspecified,
    disabledSuffixColor = Color.Unspecified,
    errorSuffixColor = Color.Unspecified,
)
