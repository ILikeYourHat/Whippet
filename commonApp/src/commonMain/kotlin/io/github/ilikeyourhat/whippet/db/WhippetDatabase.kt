package io.github.ilikeyourhat.whippet.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.ilikeyourhat.whippet.db.calendar.CalendarDao
import io.github.ilikeyourhat.whippet.db.calendar.CalendarEventEntity
import io.github.ilikeyourhat.whippet.db.converter.DateTimeConverters

@Database(
    entities = [
        CalendarEventEntity::class
    ],
    version = 3
)
@TypeConverters(
    DateTimeConverters::class
)
abstract class WhippetDatabase : RoomDatabase() {
    abstract fun getCalendarDao(): CalendarDao

    companion object {
        const val DATABASE_FILE = "whippet.db"
    }
}
