package com.example.demoapplication.data.respository

import com.example.demoapplication.data.dao.NoteDao
import com.example.demoapplication.data.dao.UserDao
import com.example.demoapplication.data.entity.NoteEntity
import com.example.demoapplication.data.entity.UserEntity
import com.example.demoapplication.network.RetrofitClient
import com.example.demoapplication.util.Output
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class UserRepository constructor (
    val userDao: UserDao
){

    suspend fun fetchUsers() = userDao.fetchUsers()


    suspend fun insertUser(userEntity: UserEntity) = userDao.upsertUser(userEntity)

    suspend fun deleteAllUsers() = userDao.deleteAllUsers()



}