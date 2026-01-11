package io.github.ilikeyourhat.whippet.di

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph

@DependencyGraph(AppScope::class)
interface JvmAppGraph : AppGraph {

//        @Provides
//        private fun providesDatabaseBuilder(
//            impl: DatabaseBuilderProviderImpl
//        ): DatabaseBuilderProvider = impl
}