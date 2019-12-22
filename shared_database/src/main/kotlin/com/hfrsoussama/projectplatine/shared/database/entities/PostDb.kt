package com.hfrsoussama.projectplatine.shared.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostDb(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @ColumnInfo(name = "userId")
    val userId: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "body")
    val body: String
)