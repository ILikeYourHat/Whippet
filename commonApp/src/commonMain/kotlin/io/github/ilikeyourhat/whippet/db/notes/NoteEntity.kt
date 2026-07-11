package io.github.ilikeyourhat.whippet.db.notes

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String? = null,
    val isGroup: Boolean = false,
    val groupId: Long? = null,
    val value: String? = null
)
