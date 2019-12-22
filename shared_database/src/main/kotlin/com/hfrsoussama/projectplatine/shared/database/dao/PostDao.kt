package com.hfrsoussama.projectplatine.shared.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hfrsoussama.projectplatine.shared.database.entities.PostDb

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postDb: PostDb)

    @Query("SELECT * FROM post")
    suspend fun getAllPostsDb(): List<PostDb>

    @Query("SELECT * FROM post WHERE id=:postId")
    suspend fun getPostDbById(postId: Long): PostDb

    @Query("SELECT COUNT(*) FROM post")
    suspend fun getPostsDbCount(): Int

}
