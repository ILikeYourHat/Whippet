package io.github.ilikeyourhat.whippet.di

import android.content.Context
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import io.github.ilikeyourhat.whippet.db.AndroidDatabaseBuilderProvider
import io.github.ilikeyourhat.whippet.db.DatabaseBuilderProvider

@DependencyGraph(AppScope::class)
interface AndroidAppGraph : AppGraph {

    @Provides
    private fun providesDatabaseBuilder(
        impl: AndroidDatabaseBuilderProvider
    ): DatabaseBuilderProvider = impl

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(@Provides context: Context): AndroidAppGraph
    }
}
