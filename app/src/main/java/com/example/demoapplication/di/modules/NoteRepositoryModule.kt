package com.example.demoapplication.di.modules

import com.example.demoapplication.data.dao.NoteDao
import com.example.demoapplication.data.respository.NoteRepository
import com.example.demoapplication.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NoteRepositoryModule {

    @Singleton
    @Provides
    fun provideNoteRepository(retrofitClient: RetrofitClient, noteDao: NoteDao)=
        NoteRepository(noteDao, retrofitClient)

}