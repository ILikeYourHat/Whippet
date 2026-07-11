package io.github.ilikeyourhat.whippet.db.converter

import androidx.room3.ColumnTypeConverter
import kotlinx.datetime.LocalDate

class DateTimeConverters {

    @ColumnTypeConverter
    fun stringToLocalDate(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }

    @ColumnTypeConverter
    fun localDateToString(date: LocalDate?): String? {
        return date?.toString()
    }
}
