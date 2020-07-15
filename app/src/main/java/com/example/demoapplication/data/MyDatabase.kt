package com.example.demoapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.demoapplication.data.dao.NoteDao
import com.example.demoapplication.data.dao.UserDao
import com.example.demoapplication.data.entity.NoteEntity
import com.example.demoapplication.data.entity.UserEntity

@Database(entities = [NoteEntity::class, UserEntity::class],version = 2,exportSchema = false)
abstract class MyDatabase : RoomDatabase(){

    abstract fun getNoteDao(): NoteDao

    abstract fun getUserDao(): UserDao


    companion object{
        const val DB_NAME = "notes_db"
    }
}