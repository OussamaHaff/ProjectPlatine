package com.hfrsoussama.projectplatine.shared.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.*
import com.hfrsoussama.projectplatine.shared.database.PostsDatabase
import com.hfrsoussama.projectplatine.shared.database.entities.PostDb
import com.hfrsoussama.projectplatine.shared.database.entities.UserDb
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    lateinit var database: PostsDatabase
    lateinit var userDao: UserDao
    lateinit var postDao: PostDao

    private val userDb = UserDb(
        userId = 1L,
        username = "LeSam",
        email = "bob@bob.com",
        name = "Ouss",
        address = null,
        phone = null,
        webSite = null,
        company = null
    )

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, PostsDatabase::class.java
        ).build()

        userDao = database.userDao()
        postDao = database.postDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun testOneUserWriteRead() = runBlocking {
        userDao.insert(userDb)
        assertThat(userDao.getUserDbById(userDb.userId)).isNotNull()
    }

    @Test
    fun nullValueIsReturnedWhenNoUserFound() = runBlocking {
        userDao.insert(userDb)
        assertThat(userDao.getUserDbById(userDb.userId + 1)).isNull()
    }

    @Test
    fun insertingAUserWithTheSameUserIdUpdatesExistingOne() = runBlocking {
        val cloneUserDb = userDb.copy(name = "sam")
        userDao.insert(userDb)
        userDao.insert(cloneUserDb)

        val savedUserDb = userDao.getUserDbById(userDb.userId)
        assertThat(savedUserDb.name).isEqualTo(cloneUserDb.name)
    }

    @Test
    fun insertingAUserWithTheSameEmailReplacesExistingOne() = runBlocking {
        val newUser = userDb.copy(userId = userDb.userId + 1)
        userDao.insert(userDb)
        userDao.insert(newUser)

        val savedUsers = userDao.getAllUsersDb()
        assertThat(savedUsers).hasSize(1)
        assertThat(savedUsers.first().userId).isEqualTo(userDb.userId + 1)
    }

    @Test
    fun actuallyGetsAllUsers() = runBlocking {
        for (id in 1L..10) {
            userDao.insert(userDb.copy(userId = id, email = "bob$id@bob.com"))
        }

        val savedUsersList = userDao.getAllUsersDb()
        assertThat(savedUsersList).hasSize(10)
    }

    @Test
    fun actuallyGetsAllUsersWithTheirPosts() = runBlocking {
        val postDb = PostDb(
            postId = 99L, userWriterId = userDb.userId, title = "New journey", body = "Anything you want"
        )
        val secondPost = postDb.copy(postId = postDb.postId + 1)

        userDao.insert(userDb)
        postDao.insert(postDb)
        postDao.insert(secondPost)

        val usersWithPostsList = userDao.getAllUsersWithPosts()
        assertThat(usersWithPostsList).hasSize(1)
        assertThat(usersWithPostsList.first().userDb.userId).isEqualTo(userDb.userId)
        assertThat(usersWithPostsList.first().postsListDb).hasSize(2)
    }

    @Test
    fun emptyListOfPostIsReturnedWhenUserDoesntHaveAnyPost() = runBlocking {
        userDao.insert(userDb)

        val usersWithPostsList = userDao.getAllUsersWithPosts()
        assertThat(usersWithPostsList).hasSize(1)
        assertThat(usersWithPostsList.first().postsListDb).hasSize(0)
    }

    @Test
    fun actuallyDeletesAllUserRecord() = runBlocking {
        userDao.insert(userDb)
        userDao.deleteAllUsersDb()

        val userList = userDao.getAllUsersDb()
        assertThat(userList).hasSize(0)
    }

    @Test
    fun userPostsCantBeRetrievedAfterDeletingUser() = runBlocking {
        val postDb = PostDb(
            postId = 99L, userWriterId = userDb.userId, title = "New journey", body = "Anything you want"
        )
        val secondPost = postDb.copy(postId = postDb.postId + 1)

        userDao.insert(userDb)
        postDao.insert(postDb)
        postDao.insert(secondPost)

        userDao.deleteAllUsersDb()

        val usersWithPostsList = userDao.getAllUsersWithPosts()
        assertThat(usersWithPostsList).hasSize(0)
    }


}