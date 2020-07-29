package com.example.demoapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "_id")
    var _id: String,

    @ColumnInfo(name = "createdAt")
    var createdAt : String,

    @ColumnInfo(name = "title")
    var title:String,

    @ColumnInfo(name = "description")
    var description : String,

    @ColumnInfo(name = "color")
    var color : String,

    @ColumnInfo(name = "priority")
    var priority : Int?,

    @ColumnInfo(name = "imageUrl")
    var imageUrl : String?

    ) {}