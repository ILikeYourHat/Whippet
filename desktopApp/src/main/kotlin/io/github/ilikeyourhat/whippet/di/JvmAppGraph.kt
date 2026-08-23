package io.github.ilikeyourhat.whippet.di

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import io.github.ilikeyourhat.whippet.db.DatabaseBuilderProvider
import io.github.ilikeyourhat.whippet.db.JvmDatabaseBuilderProvider

@DependencyGraph(AppScope::class)
interface JvmAppGraph : AppGraph {

        @Provides
        private fun providesDatabaseBuilder(
            impl: JvmDatabaseBuilderProvider
        ): DatabaseBuilderProvider = impl
}
