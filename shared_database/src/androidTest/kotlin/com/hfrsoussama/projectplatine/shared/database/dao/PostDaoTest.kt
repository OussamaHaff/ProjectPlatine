package com.hfrsoussama.projectplatine.shared.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.hfrsoussama.projectplatine.shared.database.PostsDatabase
import com.hfrsoussama.projectplatine.shared.database.entities.CommentDb
import com.hfrsoussama.projectplatine.shared.database.entities.PostDb
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
class PostDaoTest {

    private lateinit var database: PostsDatabase
    private lateinit var postDao: PostDao
    private lateinit var commentDao: CommentDao

    @Before
    fun createDatabaseWithDao() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, PostsDatabase::class.java
        ).build()
        postDao = database.postDao()
        commentDao = database.commentDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun testOnePostWriteRead() = runBlocking {
        // given a post
        val postDb = PostDb(
            postId = 99L, userWriterId = 66L, title = "New journey", body = "Anything you want"
        )

        // when written in database
        postDao.insert(postDb)
        // then it should be found
        assertThat(postDao.getPostDbById(postDb.postId)).isNotNull()
    }

    @Test
    fun postWithSameIdShouldReplaceExistingOne() = runBlocking {
        // given two posts
        val postDb = PostDb(
            postId = 99L, userWriterId = 66L, title = "New journey", body = "Anything you want"
        )
        val clonePostDb = postDb.copy(title = "Clone journey")

        // when saved in order
        postDao.insert(postDb)
        postDao.insert(clonePostDb)

        // the clone post should replace the original one
        val postAfterInsertion = postDao.getPostDbById(postDb.postId)
        assertThat(postAfterInsertion.title).isEqualTo(clonePostDb.title)
        assertThat(postDao.getPostsDbCount()).isEqualTo(1)
    }

    @Test
    fun actuallyGetAllInsertedPosts() = runBlocking {
        // when inserting 10 posts with different post ids
        val postDb = PostDb(
            postId = 99L, userWriterId = 66L, title = "New journey", body = "Anything you want"
        )
        for (i: Long in 1L..10) {
            postDao.insert(postDb.copy(postId = i))
        }

        // then we shoud get 10 posts when retrieving all posts
        assertThat(postDao.getAllPostsDb()).hasSize(10)
    }

    @Test
    fun actuallyGetAPostWithAllItsComments() = runBlocking {
        // given a post having two comments
        val postDb = PostDb(
            postId = 99L, userWriterId = 66L, title = "New journey", body = "Anything you want"
        )
        val firstCommentDb = CommentDb(
            commentId = 1L,
            postId = postDb.postId,
            authorName = "author",
            authorEmail = "b@b.com",
            body = "the comment"
        )
        val secondCommentDb = firstCommentDb.copy(commentId = 2L)

        // when inserting them to database
        postDao.insert(postDb)
        commentDao.insertBulkCommentsDb(firstCommentDb, secondCommentDb)

        // the should be able to retrieve them with post
        val postWithCommentsDb = postDao.getPostDbWithCommentsById(postDb.postId)
        assertThat(postWithCommentsDb.commentsDbList).hasSize(2)
    }

    @Test
    fun emptyListOfCommentWhenPostDoesntHaveComments() = runBlocking {
        // when inserting a post with no comments
        val postDb = PostDb(
            postId = 99L, userWriterId = 66L, title = "New journey", body = "Anything you want"
        )
        postDao.insert(postDb)

        // then it should have an empty list of comments
        val postWithComments = postDao.getPostDbWithCommentsById(postDb.postId)
        assertThat(postWithComments.commentsDbList).hasSize(0)
    }

    @Test
    fun nullPointerExceptionIsRaisedWhenPostIsNotFound() = runBlocking {
        val postDb = postDao.getPostDbById(Random(99).nextLong())
        assertThat(postDb).isNull()
    }



}


