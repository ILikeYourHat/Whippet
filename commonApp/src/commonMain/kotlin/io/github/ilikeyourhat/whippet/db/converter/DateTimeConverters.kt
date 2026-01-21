package io.github.ilikeyourhat.whippet.db.converter

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

class DateTimeConverters {

    @TypeConverter
    fun stringToLocalDate(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }

    @TypeConverter
    fun localDateToString(date: LocalDate?): String? {
        return date?.toString()
    }
}
