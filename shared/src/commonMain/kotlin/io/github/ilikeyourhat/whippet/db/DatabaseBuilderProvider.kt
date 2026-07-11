package io.github.ilikeyourhat.whippet.db

import androidx.room3.RoomDatabase

interface DatabaseBuilderProvider {
    fun provideDatabaseBuilder(): RoomDatabase.Builder<WhippetDatabase>
}
