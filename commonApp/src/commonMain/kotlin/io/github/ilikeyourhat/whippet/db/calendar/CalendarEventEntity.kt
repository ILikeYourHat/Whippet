package io.github.ilikeyourhat.whippet.db.calendar

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate

@Entity
data class CalendarEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val text: String,
    val date: LocalDate,
    val completed: Boolean = false
)
