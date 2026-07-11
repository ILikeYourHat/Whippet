package io.github.ilikeyourhat.whippet.db.notes

import androidx.room3.Dao
import androidx.room3.Query
import androidx.room3.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes WHERE groupId IS :groupId")
    fun getAll(groupId: Long?): Flow<List<NoteEntity>>

    @Upsert
    suspend fun insertOrReplace(note: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun delete(id: Long)
}
