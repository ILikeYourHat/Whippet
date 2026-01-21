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
    suspend fun insert(item: CalendarEventEntity)

    @Query("SELECT * FROM CalendarEventEntity WHERE date = :date")
    suspend fun getByDate(date: LocalDate): CalendarEventEntity?

    @Query("SELECT * FROM CalendarEventEntity")
    fun observe(): Flow<List<CalendarEventEntity>>

    @Query("DELETE FROM CalendarEventEntity WHERE date = :date")
    suspend fun clearByDate(date: LocalDate)
}
