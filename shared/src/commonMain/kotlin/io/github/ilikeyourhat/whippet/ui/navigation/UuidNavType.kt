package io.github.ilikeyourhat.whippet.ui.navigation

import androidx.navigation.NavType
import androidx.savedstate.SavedState
import androidx.savedstate.read
import androidx.savedstate.write
import kotlin.uuid.Uuid

object UuidNavType : NavType<Uuid?>(isNullableAllowed = true) {

    override fun get(bundle: SavedState, key: String): Uuid? {
        return bundle.read {
            getString(key)
                .takeIf { it.isNotEmpty() }
                ?.let(Uuid::parse)
        }
    }

    override fun parseValue(value: String): Uuid =
        Uuid.parse(value)

    override fun serializeAsValue(value: Uuid?): String =
        value?.toString() ?: ""

    override fun put(bundle: SavedState, key: String, value: Uuid?) {
        bundle.write {
            putString(key, value?.toString().orEmpty())
        }
    }
}
