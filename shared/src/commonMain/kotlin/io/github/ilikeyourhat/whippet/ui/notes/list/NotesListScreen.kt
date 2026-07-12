package io.github.ilikeyourhat.whippet.ui.notes.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.zacsweers.metrox.viewmodel.metroViewModel
import io.github.ilikeyourhat.whippet.db.notes.NoteEntity
import io.github.ilikeyourhat.whippet.ui.common.Note
import io.github.ilikeyourhat.whippet.ui.common.NoteGroup

@Composable
fun NotesListScreen(
    modifier: Modifier = Modifier,
    viewModel: NotesListViewModel = metroViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    NotesListScreen(
        state = state,
        modifier = modifier,
        onAddNoteClick = viewModel::onAddNoteClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListScreen(
    state: NotesListScreenState,
    modifier: Modifier = Modifier,
    onAddNoteClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Column {
        TopAppBar(
            title = {
                Text("Whippet!!!")
            },
            navigationIcon = {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )
        Box(modifier.fillMaxSize()) {
            when (state) {
                is NotesListScreenState.Content -> NotesList(
                    state.notes,
                    Modifier.fillMaxSize()
                        .padding(16.dp)
                )

                else -> Unit
            }
            FloatingActionButton(
                onClick = onAddNoteClick,
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
}

@Composable
private fun NotesList(
    notes: List<NoteEntity>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp)
    ) {
        items(
            items = notes,
            key = { it.id }
        ) { item ->
            if (item.isGroup) {
                NoteGroup(item)
            } else {
                Note(item)
            }
        }
    }
}
