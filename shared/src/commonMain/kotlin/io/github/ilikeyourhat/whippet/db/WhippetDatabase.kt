package io.github.ilikeyourhat.whippet.db

import androidx.room3.ColumnTypeConverters
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.ConstructedBy
import androidx.room3.RoomDatabaseConstructor
import io.github.ilikeyourhat.whippet.db.converter.DateTimeConverters
import io.github.ilikeyourhat.whippet.db.converter.UuidConverter
import io.github.ilikeyourhat.whippet.db.notes.NoteEntity
import io.github.ilikeyourhat.whippet.db.notes.NotesDao

@Database(
    entities = [
        NoteEntity::class,
    ],
    version = 1
)
@ColumnTypeConverters(
    DateTimeConverters::class,
    UuidConverter::class,
)
@ConstructedBy(WhippetDatabaseConstructor::class)
abstract class WhippetDatabase : RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

    companion object {
        const val DATABASE_FILE = "whippet.db"
    }
}

expect object WhippetDatabaseConstructor : RoomDatabaseConstructor<WhippetDatabase> {
    override fun initialize(): WhippetDatabase
}
