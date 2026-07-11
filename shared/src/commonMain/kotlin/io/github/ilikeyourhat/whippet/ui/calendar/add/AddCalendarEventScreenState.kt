package io.github.ilikeyourhat.whippet.ui.calendar.add

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.Clock

data class AddCalendarEventScreenState(
    val name: String = "",
    val date: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
)
