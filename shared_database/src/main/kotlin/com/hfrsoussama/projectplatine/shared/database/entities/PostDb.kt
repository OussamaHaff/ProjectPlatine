package com.hfrsoussama.projectplatine.shared.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostDb(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "post_id")
    val postId: Long,

    @ColumnInfo(name = "user_writer_id")
    val userWriterId: Long,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "body")
    val body: String
)
