package io.github.ilikeyourhat.whippet.ui.notes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metrox.viewmodel.ManualViewModelAssistedFactory
import dev.zacsweers.metrox.viewmodel.ManualViewModelAssistedFactoryKey
import io.github.ilikeyourhat.whippet.db.notes.NotesDao
import io.github.ilikeyourhat.whippet.ui.Screen
import io.github.ilikeyourhat.whippet.ui.navigation.Navigator
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.uuid.Uuid

@AssistedInject
class NotesListViewModel(
    @Assisted val groupId: Uuid?,
    val notesDao: NotesDao,
    val navigator: Navigator
) : ViewModel() {

    @AssistedFactory
    @ManualViewModelAssistedFactoryKey
    @ContributesIntoMap(AppScope::class)
    interface Factory : ManualViewModelAssistedFactory {
        fun create(groupId: Uuid?): NotesListViewModel
    }

    val uiState: StateFlow<NotesListScreenState> = flow { emit(groupId?.let { notesDao.getById(it) }) }
        .combine(notesDao.getAll(groupId)) { group, notes ->
            NotesListScreenState.Content(group, notes)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NotesListScreenState.Loading(groupId == null),
        )

    fun onNoteGroupClick(groupId: Uuid) = viewModelScope.launch {
        navigator.navigateTo(Screen.NotesList(groupId))
    }

    fun onAddNoteClick() = viewModelScope.launch {
        navigator.navigateTo(Screen.NotesAdd(parentGroupId = groupId))
    }

    fun onAddGroupClick() = viewModelScope.launch {
        navigator.navigateTo(Screen.GroupAdd(parentGroupId = groupId))
    }

    fun onBackClick() = viewModelScope.launch {
        navigator.goBack()
    }
}
