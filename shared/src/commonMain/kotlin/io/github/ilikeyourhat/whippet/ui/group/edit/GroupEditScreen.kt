package io.github.ilikeyourhat.whippet.ui.group.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
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
                if (state.isNew) {
                    Text("Add new group")
                } else {
                    Text("Edit group")
                }
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
        )
        if (state is GroupEditScreenState.Content) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    16.dp,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp)
            ) {
                val titleState = rememberTextFieldState(initialText = state.title)
                OutlinedTextField(
                    label = {
                        Text("Name")
                    },
                    lineLimits = TextFieldLineLimits.SingleLine,
                    state = titleState,
                    modifier = Modifier
                        .widthIn(max = 420.dp)
                        .fillMaxWidth()
                )
                LaunchedEffect(titleState) {
                    snapshotFlow { titleState.text.toString() }
                        .collect(onTitleChange)
                }
                Button(
                    onClick = onSaveClick
                ) {
                    Icon(
                        imageVector = Icons.Filled.Save,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(text = "Save")
                }
            }
        }
    }
}
