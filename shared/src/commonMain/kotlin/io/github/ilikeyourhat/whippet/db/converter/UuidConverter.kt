package io.github.ilikeyourhat.whippet.db.converter

import androidx.room3.ColumnTypeConverter
import kotlin.uuid.Uuid

class UuidConverter {

    @ColumnTypeConverter
    fun fromUuid(uuid: Uuid?): ByteArray? =
        uuid?.toByteArray()

    @ColumnTypeConverter
    fun toUuid(bytes: ByteArray?): Uuid? =
        bytes?.let(Uuid::fromByteArray)
}
