package io.github.ilikeyourhat.whippet.ui.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.zacsweers.metrox.viewmodel.metroViewModel
import io.github.ilikeyourhat.whippet.db.calendar.CalendarEventEntity

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    viewModel: CalendarViewModel = metroViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    CalendarScreen(state, modifier)
}

@Composable
fun CalendarScreen(
    state: CalendarScreenState,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is CalendarScreenState.Content -> EventList(state.events, modifier)
        else -> Unit
    }
}

@Composable
private fun EventList(
    events: List<CalendarEventEntity>,
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.text,
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = item.date.toString(),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}
