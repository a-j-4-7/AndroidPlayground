package com.example.demoapplication.data.dao

import androidx.room.*
import com.example.demoapplication.data.entity.NoteEntity
import com.example.demoapplication.data.models.NoteTuple
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(note:NoteEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNotes(notes:List<NoteEntity>)

    @Query("SELECT * FROM notes")
    fun fetchNotes(): Flow<List<NoteEntity>>

    @Query("SELECT _id,createdAt FROM notes")
    fun fetchNotesWithIdAndDate():Flow<List<NoteTuple>>


}