package io.github.ilikeyourhat.whippet

import android.app.Application
import dev.zacsweers.metro.createGraphFactory
import io.github.ilikeyourhat.whippet.di.AndroidAppGraph

class WhippetApplication : Application() {

    val appGraph by lazy {
        createGraphFactory<AndroidAppGraph.Factory>().create(this)
    }
}
