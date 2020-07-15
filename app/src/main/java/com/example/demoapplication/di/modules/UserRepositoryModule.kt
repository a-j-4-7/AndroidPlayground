package com.example.demoapplication.di.modules

import com.example.demoapplication.data.dao.NoteDao
import com.example.demoapplication.data.dao.UserDao
import com.example.demoapplication.data.respository.NoteRepository
import com.example.demoapplication.data.respository.UserRepository
import com.example.demoapplication.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UserRepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao)=
        UserRepository(userDao)

}