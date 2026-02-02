package io.github.ilikeyourhat.whippet.ui.calendar.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metrox.viewmodel.ViewModelKey
import io.github.ilikeyourhat.whippet.db.calendar.CalendarDao
import io.github.ilikeyourhat.whippet.db.calendar.CalendarEventEntity
import io.github.ilikeyourhat.whippet.ui.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

@Inject
@ContributesIntoMap(AppScope::class)
@ViewModelKey(AddCalendarEventViewModel::class)
class AddCalendarEventViewModel(
    val calendarDao: CalendarDao,
    val navigator: Navigator
) : ViewModel() {
    val uiState = MutableStateFlow(AddCalendarEventScreenState())

    fun onNameChange(name: String) {
        uiState.value = uiState.value.copy(name = name)
    }

    fun onDateChange(date: LocalDate) {
        uiState.value = uiState.value.copy(date = date)
    }

    fun onBackClick() {
        viewModelScope.launch {
            navigator.goBack()
        }
    }

    fun onSaveClick() {
        viewModelScope.launch {
            val entity = uiState.value.let {
                CalendarEventEntity(date = it.date, text = it.name)
            }
            calendarDao.insert(entity)
            navigator.goBack()
        }
    }
}
