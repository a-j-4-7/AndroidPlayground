package com.example.demoapplication.di.modules

import android.content.Context
import androidx.room.Room
import com.example.demoapplication.data.MyDatabase
import com.example.demoapplication.data.dao.NoteDao
import com.example.demoapplication.data.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideNotesDatabase(@ApplicationContext context:Context) : MyDatabase = Room.databaseBuilder(
        context,
        MyDatabase::class.java,
        MyDatabase.DB_NAME
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideNoteDao(myDatabase:MyDatabase):NoteDao {
        return myDatabase.getNoteDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(myDatabase:MyDatabase):UserDao {
        return myDatabase.getUserDao()
    }
}