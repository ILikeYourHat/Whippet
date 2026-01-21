package io.github.ilikeyourhat.whippet.db

import androidx.room.Room
import androidx.room.RoomDatabase
import dev.zacsweers.metro.Inject
import net.harawata.appdirs.AppDirsFactory
import java.io.File

@Inject
class JvmDatabaseBuilderProvider : DatabaseBuilderProvider {

    override fun provideDatabaseBuilder(): RoomDatabase.Builder<WhippetDatabase> {
        val appDir = AppDirsFactory.getInstance()
            .getUserDataDir(APP_DIRECTORY_NAME, null, APP_AUTHOR_NAME)

        val dbFile = File(appDir, WhippetDatabase.DATABASE_FILE)
        return Room.databaseBuilder<WhippetDatabase>(
            name = dbFile.absolutePath,
        )
    }

    private companion object {
        const val APP_DIRECTORY_NAME = "Whippet"
        const val APP_AUTHOR_NAME = "ILikeYourHat"
    }
}
