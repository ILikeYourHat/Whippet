package io.github.ilikeyourhat.whippet.ui.calendar.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.zacsweers.metrox.viewmodel.assistedMetroViewModel
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

@Composable
fun AddCalendarEventScreen(
    eventId: Long?,
    modifier: Modifier = Modifier,
    viewModel: AddCalendarEventViewModel = assistedMetroViewModel<AddCalendarEventViewModel, AddCalendarEventViewModel.Factory> {
        create(eventId)
    }
) {
    val state by viewModel.uiState.collectAsState()
    AddCalendarEventScreen(
        state = state,
        modifier = modifier,
        onNameChange = viewModel::onNameChange,
        onDateChange = viewModel::onDateChange,
        onBackClick = viewModel::onBackClick,
        onSaveClick = viewModel::onSaveClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCalendarEventScreen(
    state: AddCalendarEventScreenState,
    modifier: Modifier = Modifier,
    onNameChange: (String) -> Unit = {},
    onDateChange: (LocalDate) -> Unit = {},
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {}
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
            },
            actions = {
                IconButton(
                    onClick = onSaveClick
                ) {
                    Icon(
                        imageVector = Icons.Filled.Save,
                        contentDescription = null
                    )
                }
            }
        )
        Column(modifier = Modifier.padding(16.dp)) {
            val nameState = rememberTextFieldState(initialText = state.name)
            TextField(
                state = nameState,
            )
            onNameChange(nameState.text.toString())
            val datePickerState = rememberDatePickerState(state.date.toJavaLocalDate())
            onDateChange(datePickerState.localDate())
            DatePicker(
                state = datePickerState,
            )
        }
    }
}

private fun DatePickerState.localDate(): LocalDate {
    return Instant.fromEpochMilliseconds(selectedDateMillis!!)
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date
}