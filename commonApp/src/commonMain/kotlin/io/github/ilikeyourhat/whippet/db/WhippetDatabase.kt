package io.github.ilikeyourhat.whippet.db

import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.TypeConverters
import androidx.room3.ConstructedBy
import androidx.room3.RoomDatabaseConstructor
import io.github.ilikeyourhat.whippet.db.calendar.CalendarDao
import io.github.ilikeyourhat.whippet.db.calendar.CalendarEventEntity
import io.github.ilikeyourhat.whippet.db.converter.DateTimeConverters
import io.github.ilikeyourhat.whippet.db.notes.NoteEntity
import io.github.ilikeyourhat.whippet.db.notes.NotesDao

@Database(
    entities = [
        CalendarEventEntity::class,
        NoteEntity::class,
    ],
    version = 4
)
@TypeConverters(
    DateTimeConverters::class
)
@ConstructedBy(WhippetDatabaseConstructor::class)
abstract class WhippetDatabase : RoomDatabase() {
    abstract fun getCalendarDao(): CalendarDao

    abstract fun getNotesDao(): NotesDao

    companion object {
        const val DATABASE_FILE = "whippet.db"
    }
}

expect object WhippetDatabaseConstructor : RoomDatabaseConstructor<WhippetDatabase> {
    override fun initialize(): WhippetDatabase
}
