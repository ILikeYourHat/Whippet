package io.github.ilikeyourhat.whippet.ui

import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_NO
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewWrapper

@Preview(
    name = "Light",
    group = "App theme",
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark",
    group = "App theme",
    uiMode = UI_MODE_NIGHT_YES
)
@PreviewWrapper(WhippetThemePreviewWrapper::class)
annotation class AppPreview