package io.github.ilikeyourhat.whippet.db.notes

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import kotlin.uuid.Uuid

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey
    val id: Uuid = Uuid.random(),
    val title: String? = null,
    val isGroup: Boolean = false,
    val groupId: Long? = null,
    val value: String? = null
)
