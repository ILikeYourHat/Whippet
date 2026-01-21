package io.github.ilikeyourhat.whippet.db

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.Dispatchers

@Inject
class WhippetDatabaseFactory(
    private val provider: DatabaseBuilderProvider
) {

    fun create(): WhippetDatabase {
        return provider.provideDatabaseBuilder()
            .fallbackToDestructiveMigration(dropAllTables = true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}
