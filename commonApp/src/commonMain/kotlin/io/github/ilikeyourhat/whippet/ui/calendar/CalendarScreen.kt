package io.github.ilikeyourhat.whippet.ui.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import dev.zacsweers.metrox.viewmodel.metroViewModel
import io.github.ilikeyourhat.whippet.db.calendar.CalendarEventEntity

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    viewModel: CalendarViewModel = metroViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    CalendarScreen(
        state = state,
        modifier = modifier,
        onAddEventClick = viewModel::onAddEventClick,
        onCompleteClick = viewModel::onCompleteClick
    )
}

@Composable
fun CalendarScreen(
    state: CalendarScreenState,
    modifier: Modifier = Modifier,
    onAddEventClick: () -> Unit = {},
    onCompleteClick: (id: Long, completed: Boolean) -> Unit = { _, _ -> }
) {
    Box(modifier.fillMaxSize()) {
        when (state) {
            is CalendarScreenState.Content -> EventList(
                state.events,
                onCompleteClick,
                Modifier.fillMaxSize()
            )

            else -> Unit
        }
        FloatingActionButton(
            onClick = onAddEventClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun EventList(
    events: List<CalendarEventEntity>,
    onCompleteClick: (id: Long, completed: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp)
    ) {
        items(
            items = events,
            key = { it.id }
        ) { item ->
            CalendarEvent(item, onCompleteClick, Modifier.animateItem())
        }
    }
}

@Composable
private fun CalendarEvent(
    event: CalendarEventEntity,
    onCompleteClick: (id: Long, completed: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val style = if (event.completed) {
            MaterialTheme.typography.headlineSmall
                .copy(color = Color.Gray)
                .copy(textDecoration = TextDecoration.LineThrough)
        } else {
            MaterialTheme.typography.headlineSmall
        }

        Checkbox(
            checked = event.completed,
            onCheckedChange = { onCompleteClick(event.id, it) }
        )
        Text(
            text = event.text,
            style = style,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = event.date.toString(),
            style = style
        )
    }
}
