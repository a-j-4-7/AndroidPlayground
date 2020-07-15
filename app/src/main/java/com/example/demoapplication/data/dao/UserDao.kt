package com.example.demoapplication.data.dao

import androidx.room.*
import com.example.demoapplication.data.entity.NoteEntity
import com.example.demoapplication.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertUser(userEntity: UserEntity) : Long

    @Query("SELECT * FROM users")
    fun fetchUsers(): Flow<List<UserEntity>>

    @Delete
    fun deleteUser(userEntity: UserEntity)

    @Query("DELETE FROM users")
    fun deleteAllUsers()


}