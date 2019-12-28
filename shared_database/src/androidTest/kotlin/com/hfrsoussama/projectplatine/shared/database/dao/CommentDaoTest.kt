package com.hfrsoussama.projectplatine.shared.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.*
import com.hfrsoussama.projectplatine.shared.database.PostsDatabase
import com.hfrsoussama.projectplatine.shared.database.entities.CommentDb
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CommentDaoTest {

    lateinit var database: PostsDatabase
    lateinit var commentDao: CommentDao
    private val commentDb = CommentDb(
        commentId = 99L,
        postId = 66L,
        authorName = "ouss",
        authorEmail = "b@b.com",
        body = "The comment body"
    )

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, PostsDatabase::class.java
        ).build()
        commentDao = database.commentDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun testOneCommentWriteRead() = runBlocking {
        commentDao.insertCommentDb(commentDb)
        assertThat(commentDao.getCommentById(commentDb.commentId)).isNotNull()
    }

    @Test
    fun insertCommentWithTheSameIdReplacesOldComment() = runBlocking {
        val cloneCommentDb = commentDb.copy(body = "The new body")
        commentDao.insertCommentDb(commentDb)
        commentDao.insertCommentDb(cloneCommentDb)
        val savedComment = commentDao.getCommentById(commentDb.commentId)
        assertThat(savedComment.body).isEqualTo(cloneCommentDb.body)
    }

    @Test
    fun insertBulkCommentsActuallyInsertAllOfThem() = runBlocking {
        val commentsList = mutableListOf<CommentDb>()
        for (id in 1L..10) {
            commentsList.add(commentDb.copy(commentId = id))
        }
        commentDao.insertBulkCommentsDb(*commentsList.toTypedArray())
        val retrievedComments = commentDao.getAllCommentsDb()
        assertThat(retrievedComments).hasSize(10)
    }

}
