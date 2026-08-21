package io.github.ilikeyourhat.whippet.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StickyNote2
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.ilikeyourhat.whippet.db.notes.NoteEntity
import kotlin.uuid.Uuid

@Composable
fun Note(
    note: NoteEntity,
    contract: NoteContract = NoteContract.Empty,
    modifier: Modifier = Modifier
) {
    NoteCard(
        icon = Icons.AutoMirrored.Filled.StickyNote2,
        id = note.id,
        title = note.title.orEmpty(),
        value = note.value,
        contract = contract,
        modifier = modifier
    )
}

@Composable
fun NoteGroup(
    note: NoteEntity,
    contract: NoteContract = NoteContract.Empty,
    modifier: Modifier = Modifier
) {
    NoteCard(
        icon = Icons.Filled.Folder,
        id = note.id,
        title = note.title.orEmpty(),
        clickable = true,
        contract = contract,
        modifier = modifier
    )
}

@Composable
fun NoteCard(
    icon: ImageVector,
    id: Uuid,
    title: String,
    value: String? = null,
    clickable: Boolean = false,
    contract: NoteContract,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        border = BorderStroke(2.dp, Color.Black),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .clickable(
                    enabled = clickable,
                    onClick = { contract.onItemClick(id) }
                )
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .weight(1f)
                        .minimumInteractiveComponentSize()
                        .padding(vertical = 8.dp)
                )
                DropdownMenuButton(
                    listOf(
                        DropdownItemContent("Edit") { contract.onItemEdit(id) },
                        DropdownItemContent("Delete") { contract.onItemDelete(id) }
                    )
                )
            }
            if (value != null) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun Note_withValue() {
    Note(
        note = NoteEntity(
            title = "Example",
            value = "This is some long text"
        )
    )
}

@Preview
@Composable
fun Note_withoutValue() {
    Note(
        note = NoteEntity(
            title = "Example"
        )
    )
}

@Preview
@Composable
fun Note_withLongTitle() {
    Note(
        note = NoteEntity(
            title = "Example exampleeeeeeeeeeeeeeeeeeeee",
            value = "This is some long text aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        )
    )
}

@Preview
@Composable
fun Note_group() {
    NoteGroup(
        note = NoteEntity(
            title = "Example"
        )
    )
}
