package com.hfrsoussama.projectplatine.shared.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
data class CommentDb (
    @PrimaryKey
    @ColumnInfo(name = "comment_id")
    val commentId: Long,

    @ColumnInfo(name = "parent_post_id")
    val postId: Long,

    @ColumnInfo(name = "author_name")
    val authorName: String,

    @ColumnInfo(name = "author_email")
    val authorEmail: String,

    @ColumnInfo(name = "body")
    val body: String
)

