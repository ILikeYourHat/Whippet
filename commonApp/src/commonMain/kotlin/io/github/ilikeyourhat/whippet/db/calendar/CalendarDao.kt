package io.github.ilikeyourhat.whippet.db.calendar

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

@Dao
interface CalendarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CalendarEntryEntity)

    @Query("SELECT * FROM CalendarEntryEntity WHERE date = :date")
    suspend fun getByDate(date: LocalDate): CalendarEntryEntity?

    @Query("SELECT * FROM CalendarEntryEntity")
    fun observe(): Flow<List<CalendarEntryEntity>>

    @Query("DELETE FROM CalendarEntryEntity WHERE date = :date")
    suspend fun clearByDate(date: LocalDate)
}
