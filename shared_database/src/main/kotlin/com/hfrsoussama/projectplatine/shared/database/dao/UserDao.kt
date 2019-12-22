package com.hfrsoussama.projectplatine.shared.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hfrsoussama.projectplatine.shared.database.entities.UserDb
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userDb: UserDb)

    @Query("SELECT * FROM user")
    fun getAllUsersDb() : Flow<List<UserDb>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserDbById(id: Long): Flow<UserDb>

    @Query("DELETE FROM user")
    fun deleteAllUsersDb()
}
