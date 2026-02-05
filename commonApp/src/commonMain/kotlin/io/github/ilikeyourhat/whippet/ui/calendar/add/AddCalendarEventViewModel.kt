package io.github.ilikeyourhat.whippet.ui.calendar.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metrox.viewmodel.ManualViewModelAssistedFactory
import dev.zacsweers.metrox.viewmodel.ManualViewModelAssistedFactoryKey
import io.github.ilikeyourhat.whippet.db.calendar.CalendarDao
import io.github.ilikeyourhat.whippet.db.calendar.CalendarEventEntity
import io.github.ilikeyourhat.whippet.ui.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

@AssistedInject
class AddCalendarEventViewModel(
    @Assisted val eventId: Long?,
    val calendarDao: CalendarDao,
    val navigator: Navigator
) : ViewModel() {

    @AssistedFactory
    @ManualViewModelAssistedFactoryKey(Factory::class)
    @ContributesIntoMap(AppScope::class)
    interface Factory : ManualViewModelAssistedFactory {
        fun create(eventId: Long?): AddCalendarEventViewModel
    }

    init {
        viewModelScope.launch {
            if (eventId != null) {
                val event = calendarDao.getById(eventId)
                uiState.value = AddCalendarEventScreenState(
                    name = event.text,
                    date = event.date
                )
            }
        }
    }

    val uiState = MutableStateFlow(
        AddCalendarEventScreenState()
    )

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
                CalendarEventEntity(
                    id = eventId,
                    date = it.date,
                    text = it.name
                )
            }
            calendarDao.insert(entity)
            navigator.goBack()
        }
    }
}
