package com.hfrsoussama.projectplatine.feat.posts.core.network.comment

import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.CommentWs
import com.hfrsoussama.projectplatine.feat.posts.core.network.MixedSelectiveCommentRetrievalStrategy
import com.hfrsoussama.projectplatine.feat.posts.core.network.PostsWebServices
import com.hfrsoussama.projectplatine.shared.database.dao.CommentDao
import com.hfrsoussama.projectplatine.shared.database.entities.CommentDb
import com.hfrsoussama.projectplatine.shared.database.entities.PostDb
import com.hfrsoussama.projectplatine.shared.database.entities.PostWithCommentsDb
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MixedSelectiveCommentRetrievalStrategyTest {

    private val remoteClient = mockk<PostsWebServices>()
    private val commentDao = mockk<CommentDao>()

    private val postId = 1L
    private val mixedSelectiveStrategy =
        MixedSelectiveCommentRetrievalStrategy(
            postId
        )

    @Before
    fun setUp() {
        coEvery {
            remoteClient.getCommentsByPostId(postId)
        } returns listOf(
            CommentWs(
                postId = postId,
                id= 1,
                name = "dirst comment author",
                email = "b@b.com",
                body = "first comment body"
            ),
            CommentWs(
                postId = postId,
                id = 2,
                name = "second comment title",
                email = "b@b.com",
                body = "second comment body"
            )
        )

        coEvery {
            commentDao.insertCommentDb(any())
        } just Runs

        coEvery {
            commentDao.getAllCommentsDbForPost(postId)
        } returns listOf(
            PostWithCommentsDb(
                postDb = PostDb(postId = postId, userWriterId = 1L, title = "post title", body = "post body"),
                commentsDbList = listOf(
                    CommentDb(
                        commentId = 1,
                        postId = postId,
                        authorName = "first comment title",
                        authorEmail = "b@b.com",
                        body = "first comment body"
                    ),
                    CommentDb(
                        commentId = 2,
                        postId = postId,
                        authorName = "second comment title",
                        authorEmail = "b@b.com",
                        body = "second comment body"
                    )
                )
            )
        )
    }

    @Test
    fun `should make a web service call then save to database then read from database`() = runBlocking {
        mixedSelectiveStrategy.retrieveComments(remoteClient, commentDao)
        coVerifyOrder {
            remoteClient.getCommentsByPostId(postId)
            commentDao.insertCommentDb(any())
            commentDao.getAllCommentsDbForPost(postId)
        }

    }
}