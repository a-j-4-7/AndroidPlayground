package com.example.demoapplication.data.models

import androidx.room.ColumnInfo

data class NoteTuple(
    @ColumnInfo(name = "_id")
    val id:Int,
    @ColumnInfo(name = "createdAt")
    val createdAt:String
)