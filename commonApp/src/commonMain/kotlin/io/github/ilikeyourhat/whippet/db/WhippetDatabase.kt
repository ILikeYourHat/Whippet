package io.github.ilikeyourhat.whippet.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.ilikeyourhat.whippet.db.calendar.CalendarDao
import io.github.ilikeyourhat.whippet.db.calendar.CalendarEntryEntity

@Database(
    entities = [CalendarEntryEntity::class],
    version = 1
)
abstract class WhippetDatabase : RoomDatabase() {
    abstract fun getCalendarDao(): CalendarDao

    companion object {
        const val DATABASE_FILE = "whippet.db"
    }
}
