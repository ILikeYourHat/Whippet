package io.github.ilikeyourhat.whippet.db

import androidx.room.RoomDatabase

interface DatabaseBuilderProvider {
    fun provideDatabaseBuilder(): RoomDatabase.Builder<WhippetDatabase>
}
