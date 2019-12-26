package com.hfrsoussama.projectplatine.shared.database.dao

import androidx.room.*
import com.hfrsoussama.projectplatine.shared.database.entities.UserDb
import com.hfrsoussama.projectplatine.shared.database.entities.UserWithPostsListDB

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userDb: UserDb)

    @Query("SELECT * FROM user")
    suspend fun getAllUsersDb() : List<UserDb>

    @Transaction
    @Query("SELECT * FROM user")
    suspend fun getAllUsersWithPosts(): List<UserWithPostsListDB>

    @Query("SELECT * FROM user WHERE user_id = :id")
    suspend fun getUserDbById(id: Long): UserDb

    @Query("DELETE FROM user")
    suspend fun deleteAllUsersDb()

}
