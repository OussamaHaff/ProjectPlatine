package com.hfrsoussama.projectplatine.shared.database.dao

import androidx.room.*
import com.hfrsoussama.projectplatine.shared.database.entities.PostDb
import com.hfrsoussama.projectplatine.shared.database.entities.PostWithCommentsDb

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postDb: PostDb)

    @Query("SELECT * FROM post")
    suspend fun getAllPostsDb(): List<PostDb>

    @Query("SELECT * FROM post WHERE post_id=:postId")
    suspend fun getPostDbById(postId: Long): PostDb

    @Query("SELECT COUNT(*) FROM post")
    suspend fun getPostsDbCount(): Int

    @Transaction
    @Query("SELECT * FROM post WHERE post_id=:postId")
    suspend fun getPostDbWithCommentsById(postId: Long): PostWithCommentsDb

}
