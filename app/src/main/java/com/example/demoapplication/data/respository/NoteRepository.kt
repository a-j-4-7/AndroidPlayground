package com.example.demoapplication.data.respository

import com.example.demoapplication.data.dao.NoteDao
import com.example.demoapplication.data.entity.NoteEntity
import com.example.demoapplication.network.RetrofitClient
import com.example.demoapplication.util.Output
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class NoteRepository constructor (
    val noteDao: NoteDao,
    val retrofitClient: RetrofitClient
){

    suspend fun fetchNotes()
        =flow{
            emit(Output.Loading)
            kotlinx.coroutines.delay(1000)
            try {
                val notes = retrofitClient.fetchNotes()
                noteDao.upsertNotes(notes)
                emit(Output.Success(notes))
            }catch (e:Exception){
                emit(Output.Error(e))
            }
        }


    suspend fun fetchNotesAlt() = noteDao.fetchNotesWithIdAndDate()

}