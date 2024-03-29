package com.hfrsoussama.projectplatine.shared.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

import com.hfrsoussama.projectplatine.shared.database.entities.CommentDb
import com.hfrsoussama.projectplatine.shared.database.entities.PostWithCommentsDb

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommentDb(commentDb: CommentDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBulkCommentsDb(vararg commentDb: CommentDb)

    @Query("SELECT * FROM comment")
    suspend fun getAllCommentsDb(): List<CommentDb>

    @Transaction
    @Query("SELECT * FROM post WHERE post_id=:postId")
    suspend fun getAllCommentsDbForPost(postId: Long): List<PostWithCommentsDb>

    @Query("SELECT * FROM comment WHERE comment_id=:commentId")
    suspend fun getCommentById(commentId: Long): CommentDb
}
