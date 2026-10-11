package io.github.ilikeyourhat.whippet.ui.notes.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.StickyNote2
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.zacsweers.metrox.viewmodel.assistedMetroViewModel
import io.github.ilikeyourhat.whippet.db.notes.NoteEntity
import io.github.ilikeyourhat.whippet.ui.AppPreview
import io.github.ilikeyourhat.whippet.ui.WhippetTheme
import io.github.ilikeyourhat.whippet.ui.common.Note
import io.github.ilikeyourhat.whippet.ui.common.NoteContract
import io.github.ilikeyourhat.whippet.ui.common.NoteGroup
import io.github.ilikeyourhat.whippet.ui.topAppBar
import kotlin.uuid.Uuid

@Composable
fun NotesListScreen(
    groupId: Uuid? = null,
    modifier: Modifier = Modifier,
    viewModel: NotesListViewModel = assistedMetroViewModel<NotesListViewModel, NotesListViewModel.Factory> {
        create(groupId)
    }
) {
    val state by viewModel.uiState.collectAsState()
    NotesListScreen(
        state = state,
        modifier = modifier,
        contract = viewModel,
        onAddNoteClick = viewModel::onAddNoteClick,
        onAddGroupClick = viewModel::onAddGroupClick,
        onBackClick = viewModel::onBackClick,
    )
}

@Composable
fun NotesListScreen(
    state: NotesListScreenState,
    modifier: Modifier = Modifier,
    contract: NoteContract = NoteContract.Empty,
    onAddNoteClick: () -> Unit = {},
    onAddGroupClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    Column {
        TopAppBar(
            title = {
                val title = if (state is NotesListScreenState.Content) {
                    state.group?.title ?: "Whippet"
                } else {
                    "Loading..."
                }
                Text(
                    text = title,
                    color = WhippetTheme.colors.text
                )
            },
            colors = WhippetTheme.colors.topAppBar(),
            navigationIcon = {
                if (state.isRoot) {
                    Icon(
                        imageVector = Icons.Filled.Pets,
                        contentDescription = null,
                        modifier = Modifier.minimumInteractiveComponentSize()
                    )
                } else {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            }
        )
        Box(
            modifier
                .background(color = WhippetTheme.colors.background)
                .fillMaxSize()
        ) {
            when (state) {
                is NotesListScreenState.Content -> NotesList(
                    state.notes,
                    contract = contract,
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
                containerColor = WhippetTheme.colors.fab,
                onClick = { expanded = !expanded }
            ) {
                Icon(
                    imageVector = if (expanded) {
                        Icons.Filled.Close
                    } else {
                        Icons.Filled.Add
                    },
                    contentDescription = null,
                    tint = WhippetTheme.colors.icon
                )
            }
        },
        modifier = modifier
    ) {
        FloatingActionButtonMenuItem(
            containerColor = WhippetTheme.colors.fab,
            onClick = {
                expanded = false
                onAddGroupClick()
            },
            text = {
                Text(
                    text = "New group",
                    color = WhippetTheme.colors.text
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Folder,
                    contentDescription = null,
                    tint = WhippetTheme.colors.icon,
                )
            }
        )
        FloatingActionButtonMenuItem(
            containerColor = WhippetTheme.colors.fab,
            onClick = {
                expanded = false
                onAddNoteClick()
            },
            text = {
                Text(
                    text = "New note",
                    color = WhippetTheme.colors.text
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.StickyNote2,
                    contentDescription = null,
                    tint = WhippetTheme.colors.icon,
                )
            }
        )
    }
}

@Composable
private fun NotesList(
    notes: List<NoteEntity>,
    contract: NoteContract,
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
                NoteGroup(item, contract)
            } else {
                Note(item, contract)
            }
        }
    }
}

@Composable
@AppPreview
private fun `NotesListScreen with content`() {
    NotesListScreen(
        state = NotesListScreenState.Content(
            group = null,
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

@Composable
@AppPreview
private fun `NotesListScreen empty`() {
    NotesListScreen(
        state = NotesListScreenState.Content(
            group = null,
            notes = emptyList()
        )
    )
}
