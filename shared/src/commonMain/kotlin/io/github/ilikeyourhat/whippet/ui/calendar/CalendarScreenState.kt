package io.github.ilikeyourhat.whippet.ui.calendar

import io.github.ilikeyourhat.whippet.db.calendar.CalendarEventEntity

sealed class CalendarScreenState {

    data object Loading : CalendarScreenState()
    data class Content(
        val events: List<CalendarEventEntity>
    ) : CalendarScreenState()
}
