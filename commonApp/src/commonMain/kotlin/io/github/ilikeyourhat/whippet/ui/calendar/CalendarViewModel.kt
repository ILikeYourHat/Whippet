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

@Inject
@ContributesIntoMap(AppScope::class)
@ViewModelKey(CalendarViewModel::class)
class CalendarViewModel(
    val calendarDao: CalendarDao,
    val navigator: Navigator
) : ViewModel() {

    val uiState = calendarDao.observe()
        .map { entries -> entries.sortedWith(eventComparator) }
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

    fun onCompleteClick(id: Long, complete: Boolean) {
        viewModelScope.launch {
            calendarDao.markAsCompleted(id, complete)
        }
    }

    private val eventComparator: Comparator<CalendarEventEntity> = compareBy<CalendarEventEntity> { it.completed }
        .thenComparing { it.date }
        .thenComparing { it.text }
}
