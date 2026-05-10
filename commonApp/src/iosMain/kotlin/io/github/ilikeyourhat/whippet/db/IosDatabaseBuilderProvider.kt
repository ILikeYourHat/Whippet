package io.github.ilikeyourhat.whippet.db

import androidx.room3.Room
import androidx.room3.RoomDatabase
import dev.zacsweers.metro.Inject
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@Inject
class IosDatabaseBuilderProvider: DatabaseBuilderProvider {

    override fun provideDatabaseBuilder(): RoomDatabase.Builder<WhippetDatabase> {
        val dbFilePath = documentDirectory() + "/" + WhippetDatabase.DATABASE_FILE
        return Room.databaseBuilder<WhippetDatabase>(
            name = dbFilePath,
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory?.path)
    }
}
