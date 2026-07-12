package io.github.ilikeyourhat.whippet.ui.notes.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StickyNote2
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
        onAddNoteClick = viewModel::onAddNoteClick,
        onAddGroupClick = viewModel::onAddGroupClick
    )
}

@Composable
fun NotesListScreen(
    state: NotesListScreenState,
    modifier: Modifier = Modifier,
    onAddNoteClick: () -> Unit = {},
    onAddGroupClick: () -> Unit = {}
) {
    Column {
        TopAppBar(
            title = {
                Text("Whippet")
            },
            navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.Pets,
                        contentDescription = null,
                        modifier = Modifier.minimumInteractiveComponentSize()
                    )
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
            FabSection(
                onAddGroupClick = onAddGroupClick,
                onAddNoteClick = onAddNoteClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
            )
        }
    }
}

@Composable
private fun FabSection(
    onAddGroupClick: () -> Unit = {},
    onAddNoteClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    FloatingActionButtonMenu(
        expanded = expanded,
        button = {
            FloatingActionButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(
                    imageVector = if (expanded) {
                        Icons.Filled.Close
                    } else {
                        Icons.Filled.Add
                    },
                    contentDescription = null
                )
            }
        },
        modifier = modifier
    ) {
        FloatingActionButtonMenuItem(
            onClick = {
                expanded = false
                onAddGroupClick()
            },
            text = {
                Text("New group")
            },
            icon = {
                Icon(
                    Icons.Filled.Folder,
                    contentDescription = null
                )
            }
        )
        FloatingActionButtonMenuItem(
            onClick = {
                expanded = false
                onAddNoteClick()
            },
            text = {
                Text("New note")
            },
            icon = {
                Icon(
                    Icons.AutoMirrored.Filled.StickyNote2,
                    contentDescription = null
                )
            }
        )
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

@Composable
@Preview
fun NotesListScreen() {
    NotesListScreen(
        state = NotesListScreenState.Content(
            notes = listOf(
                NoteEntity(
                    title = "Group",
                    isGroup = true
                ),
                NoteEntity(
                    title = "Something"
                ),
                NoteEntity(
                    title = "Something",
                    value = "with text"
                ),
            )
        )
    )
}