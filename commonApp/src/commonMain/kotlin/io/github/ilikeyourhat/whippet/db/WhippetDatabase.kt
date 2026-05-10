package io.github.ilikeyourhat.whippet.db

import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.TypeConverters
import androidx.room3.ConstructedBy
import androidx.room3.RoomDatabaseConstructor
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
@ConstructedBy(WhippetDatabaseConstructor::class)
abstract class WhippetDatabase : RoomDatabase() {
    abstract fun getCalendarDao(): CalendarDao

    companion object {
        const val DATABASE_FILE = "whippet.db"
    }
}

@Suppress("KotlinNoActualForExpect")
expect object WhippetDatabaseConstructor : RoomDatabaseConstructor<WhippetDatabase> {
    override fun initialize(): WhippetDatabase
}
