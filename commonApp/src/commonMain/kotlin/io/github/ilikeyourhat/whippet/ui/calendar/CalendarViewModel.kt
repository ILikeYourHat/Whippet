package io.github.ilikeyourhat.whippet.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metrox.viewmodel.ViewModelKey
import io.github.ilikeyourhat.whippet.db.calendar.CalendarDao
import io.github.ilikeyourhat.whippet.db.calendar.CalendarEventEntity
import io.github.ilikeyourhat.whippet.ui.Screen
import io.github.ilikeyourhat.whippet.ui.navigation.Navigator
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.Clock

@Inject
@ContributesIntoMap(AppScope::class)
@ViewModelKey(CalendarViewModel::class)
class CalendarViewModel(
    val calendarDao: CalendarDao,
    val navigator: Navigator
) : ViewModel() {

    init {
        viewModelScope.launch {
            calendarDao.insert(
                CalendarEventEntity(
                    id = 123,
                    date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                    text = "Hello!"
                )
            )
            calendarDao.insert(
                CalendarEventEntity(
                    id = 1234,
                    date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                    text = "Hello world!"
                )
            )
        }
    }

    val uiState = calendarDao.observe()
        .map { entries -> entries.sortedByDescending { it.date } }
        .map { events -> CalendarScreenState.Content(events) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CalendarScreenState.Loading,
        )

    fun onAddEventClick() {
        viewModelScope.launch {
            navigator.navigateTo(Screen.AddCalendarEvent)
        }
    }
}
