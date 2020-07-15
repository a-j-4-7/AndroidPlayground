package com.example.demoapplication.data.dao

import androidx.room.*
import com.example.demoapplication.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(note:NoteEntity) : Long

    @Query("SELECT * FROM notes")
    fun fetchNotes(): Flow<List<NoteEntity>>


}