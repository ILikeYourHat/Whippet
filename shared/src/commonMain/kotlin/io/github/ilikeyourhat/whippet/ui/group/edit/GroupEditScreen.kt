package io.github.ilikeyourhat.whippet.ui.group.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.zacsweers.metrox.viewmodel.assistedMetroViewModel
import kotlin.uuid.Uuid

@Composable
fun GroupEditScreen(
    groupId: Uuid?,
    parentGroupId: Uuid?,
    modifier: Modifier = Modifier,
    viewModel: GroupEditViewModel = assistedMetroViewModel<GroupEditViewModel, GroupEditViewModel.Factory> {
        create(groupId, parentGroupId)
    }
) {
    val state by viewModel.uiState.collectAsState()
    GroupEditScreen(
        state = state,
        modifier = modifier,
        onTitleChange = viewModel::onTitleChange,
        onBackClick = viewModel::onBackClick,
        onSaveClick = viewModel::onSaveClick
    )
}

@Composable
fun GroupEditScreen(
    state: GroupEditScreenState,
    modifier: Modifier = Modifier,
    onTitleChange: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {}
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = {
                Text("Add new group")
            },
            navigationIcon = {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = onSaveClick
                ) {
                    Icon(
                        imageVector = Icons.Filled.Save,
                        contentDescription = null
                    )
                }
            }
        )
        Column(modifier = Modifier.padding(16.dp)) {
            val titleState = rememberTextFieldState(initialText = state.title)
            TextField(
                state = titleState,
            )
            LaunchedEffect(titleState) {
                snapshotFlow { titleState.text.toString() }
                    .collect(onTitleChange)
            }
        }
    }
}
