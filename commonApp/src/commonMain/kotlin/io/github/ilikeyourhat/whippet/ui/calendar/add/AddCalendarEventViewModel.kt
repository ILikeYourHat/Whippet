package io.github.ilikeyourhat.whippet.ui.calendar.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metrox.viewmodel.ViewModelKey
import io.github.ilikeyourhat.whippet.db.calendar.CalendarDao
import io.github.ilikeyourhat.whippet.ui.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Inject
@ContributesIntoMap(AppScope::class)
@ViewModelKey(AddCalendarEventViewModel::class)
class AddCalendarEventViewModel(
    val calendarDao: CalendarDao,
    val navigator: Navigator
) : ViewModel() {
    val uiState = MutableStateFlow(AddCalendarEventScreenState())

    fun onBackClick() {
        viewModelScope.launch {
            navigator.goBack()
        }
    }
}
