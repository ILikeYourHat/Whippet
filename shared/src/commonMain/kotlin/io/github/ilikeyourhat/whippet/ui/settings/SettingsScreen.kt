package io.github.ilikeyourhat.whippet.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.zacsweers.metrox.viewmodel.assistedMetroViewModel

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = assistedMetroViewModel()
) {
    SettingsScreen(
        modifier = modifier,
        onGitHubLinkClick = viewModel::onGitHubLinkClick
    )
}

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onGitHubLinkClick: () -> Unit
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = {
                Text("Settings")
            }
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Button(
                onClick = onGitHubLinkClick
            ) {
                Text(text = "Check me out on GitHub!")
            }
        }
    }
}
