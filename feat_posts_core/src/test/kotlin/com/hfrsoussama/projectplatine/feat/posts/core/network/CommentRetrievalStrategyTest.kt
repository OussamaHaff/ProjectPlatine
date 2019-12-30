package com.hfrsoussama.projectplatine.feat.posts.core.network

import com.google.common.truth.Truth.*
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.CommentUi
import com.hfrsoussama.projectplatine.shared.database.dao.CommentDao
import com.hfrsoussama.projectplatine.shared.database.entities.CommentDb
import com.hfrsoussama.projectplatine.shared.database.entities.PostDb
import com.hfrsoussama.projectplatine.shared.database.entities.PostWithCommentsDb
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class CommentRetrievalStrategyTest {

    private val remoteClient = mockk<PostsWebServices>()
    private val commentDao = mockk<CommentDao>()

    private val localOnlyRetrievalStrategy = LocalOnlyCommentRetrievalStrategy()

    @Before
    fun generateStubs() {
        coEvery {
            commentDao.getAllCommentsDbForPost(any())
        } answers {
            listOf(
                PostWithCommentsDb(
                    postDb = PostDb(arg(0), 1, "The post title", "the post body"),
                    commentsDbList = listOf(
                        CommentDb(1, 1, "first comment title", "b@b.com", "first comment body"),
                        CommentDb(2, 1, "second comment title", "b@b.com", "second comment body")
                    )
                )
            )
        }

        coEvery {
            commentDao.getAllCommentsDb()
        } returns listOf(
            CommentDb(1, 1, "first comment title", "b@b.com", "first comment body"),
            CommentDb(2, 1, "second comment title", "b@b.com", "second comment body")
        )

        coEvery {
            remoteClient.getCommentsByPostId(any())
        } returns listOf()
    }


    @Test
    fun `local only strategy should never call a web service` () = runBlocking {
        localOnlyRetrievalStrategy.retrieveComments(remoteClient, commentDao)
        verify {
            remoteClient wasNot Called
        }
    }

    @Test
    fun `local only strategy should call once the database`() = runBlocking {
        localOnlyRetrievalStrategy.retrieveComments(remoteClient, commentDao)
        coVerify(exactly = 1) {
            commentDao.getAllCommentsDb()
        }
    }

    @Test
    fun `local only strategy should return ui mode`() = runBlocking {
        val commentUiList = localOnlyRetrievalStrategy.retrieveComments(remoteClient, commentDao)
        assertThat(commentUiList is List<CommentUi>).isTrue()
    }

}