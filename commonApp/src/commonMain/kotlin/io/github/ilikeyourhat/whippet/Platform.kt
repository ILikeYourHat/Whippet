package io.github.ilikeyourhat.whippet

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform