package com.example.demoapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "address")
    var address:String
){
    @PrimaryKey(autoGenerate = true)
     var id:Int = 0
}