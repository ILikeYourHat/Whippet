package io.github.ilikeyourhat.whippet.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.zacsweers.metro.Inject

@Inject
class AndroidDatabaseBuilderProvider(
    private val context: Context
) : DatabaseBuilderProvider {

    override fun provideDatabaseBuilder(): RoomDatabase.Builder<WhippetDatabase> {
        val dbFile = context.getDatabasePath(WhippetDatabase.DATABASE_FILE)
        return Room.databaseBuilder<WhippetDatabase>(
            context = context,
            name = dbFile.absolutePath
        )
    }
}
