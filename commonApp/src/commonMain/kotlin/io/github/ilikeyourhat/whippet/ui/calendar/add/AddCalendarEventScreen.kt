package io.github.ilikeyourhat.whippet.ui.calendar.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.zacsweers.metrox.viewmodel.metroViewModel

@Composable
fun AddCalendarEventScreen(
    modifier: Modifier = Modifier,
    viewModel: AddCalendarEventViewModel = metroViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    AddCalendarEventScreen(
        state = state,
        modifier = modifier,
        onBackClick = viewModel::onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCalendarEventScreen(
    state: AddCalendarEventScreenState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = {
                Text("Add new calendar event")
            },
            navigationIcon = {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )
        Column(modifier = Modifier.padding(16.dp)) {
            TextField(
                state = rememberTextFieldState(initialText = "Hello"),
            )
        }
    }
}
