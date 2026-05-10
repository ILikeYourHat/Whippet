package io.github.ilikeyourhat.whippet.db.calendar

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import kotlinx.datetime.LocalDate

@Entity
data class CalendarEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val text: String,
    val date: LocalDate,
    val completed: Boolean = false
)
