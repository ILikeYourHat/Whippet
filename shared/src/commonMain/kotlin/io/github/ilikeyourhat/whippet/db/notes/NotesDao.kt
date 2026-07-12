package io.github.ilikeyourhat.whippet.db.notes

import androidx.room3.Dao
import androidx.room3.Query
import androidx.room3.Upsert
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.Uuid

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getById(id: Uuid): NoteEntity?

    @Query("SELECT * FROM notes WHERE groupId IS :groupId")
    fun getAll(groupId: Uuid?): Flow<List<NoteEntity>>

    @Upsert
    suspend fun insertOrReplace(note: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun delete(id: Uuid)
}
