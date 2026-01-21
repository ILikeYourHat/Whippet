package io.github.ilikeyourhat.whippet.di

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.github.ilikeyourhat.whippet.db.WhippetDatabase
import io.github.ilikeyourhat.whippet.db.WhippetDatabaseFactory
import io.github.ilikeyourhat.whippet.db.calendar.CalendarDao

interface AppGraph {

    @Provides
    @SingleIn(AppScope::class)
    private fun provideAppDatabase(factory: WhippetDatabaseFactory): WhippetDatabase {
        return factory.create()
    }

    @Provides
    private fun provideHappinessDao(database: WhippetDatabase): CalendarDao {
        return database.getCalendarDao()
    }
}
