package io.github.ilikeyourhat.whippet.db.calendar

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class CalendarEntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val date: String,
    val text: String
)
