package com.hfrsoussama.projectplatine.shared.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hfrsoussama.projectplatine.shared.database.dao.PostDao
import com.hfrsoussama.projectplatine.shared.database.dao.UserDao
import com.hfrsoussama.projectplatine.shared.database.entities.PostDb
import com.hfrsoussama.projectplatine.shared.database.entities.UserDb

@Database(
    entities = [UserDb::class, PostDb::class],
    version = 1,
    exportSchema = false
)
abstract class PostsDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao

    companion object {

        private const val DATABASE_FILE_NAME = "posts_db"

        @Volatile
        private var INSTANCE: PostsDatabase? = null

        fun getDatabase(context: Context): PostsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    PostsDatabase::class.java,
                    DATABASE_FILE_NAME
                ).build()
                INSTANCE = newInstance
                return newInstance
            }
        }
    }

}