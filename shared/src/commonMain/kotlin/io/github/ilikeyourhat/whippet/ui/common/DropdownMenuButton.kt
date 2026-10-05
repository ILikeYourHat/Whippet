package io.github.ilikeyourhat.whippet.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.ilikeyourhat.whippet.ui.AppPreview
import io.github.ilikeyourhat.whippet.ui.WhippetTheme

@Composable
fun DropdownMenuButton(
    dropdownContent: List<DropdownItemContent>
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(
            onClick = { expanded = true }
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                tint = WhippetTheme.colors.icon,
                contentDescription = null
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = WhippetTheme.colors.background
        ) {
            dropdownContent.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item.text,
                            color = WhippetTheme.colors.text
                        )
                    },
                    onClick = {
                        expanded = false
                        item.onClick()
                    }
                )
            }
        }
    }
}

data class DropdownItemContent(
    val text: String,
    val icon: ImageVector? = null,
    val onClick: () -> Unit = {},
)

@Composable
@AppPreview
private fun `DropdownMenuButton regular`() {
    DropdownMenuButton(
        listOf(
            DropdownItemContent("test")
        )
    )
}
