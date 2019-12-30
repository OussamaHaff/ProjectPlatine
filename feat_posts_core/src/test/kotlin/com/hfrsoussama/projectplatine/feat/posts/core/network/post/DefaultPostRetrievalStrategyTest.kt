package com.hfrsoussama.projectplatine.feat.posts.core.network.post

import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.PostWs
import com.hfrsoussama.projectplatine.feat.posts.core.network.DefaultPostRetrievalStrategy
import com.hfrsoussama.projectplatine.feat.posts.core.network.PostsWebServices
import com.hfrsoussama.projectplatine.shared.database.dao.PostDao
import com.hfrsoussama.projectplatine.shared.database.entities.PostDb
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DefaultPostRetrievalStrategyTest {

    private val remoteClient = mockk<PostsWebServices>()
    private val postDao = mockk<PostDao>()

    private val defaultPostRetrievalStrategy =
        DefaultPostRetrievalStrategy()

    @Before
    fun stubResults() {
        coEvery {
            remoteClient.getPosts()
        } returns listOf(
            PostWs(
                userId = 1L,
                id = 1L,
                title = "first post title",
                body = "first post body"
            ),
            PostWs(
                userId = 1L,
                id = 2L,
                title = "second post title",
                body = "second post body"
            )
        )

        coEvery {
            postDao.insert(any())
        } just Runs

        coEvery {
            postDao.getAllPostsDb()
        } returns listOf(
            PostDb(
                postId = 1L,
                userWriterId = 1L,
                title = "first post title",
                body = "first post body"
            ),
            PostDb(
                postId = 2L,
                userWriterId = 1L,
                title = "second post title",
                body = "second post body"
            )
        )
    }

    @Test
    fun `should call web service then save to database and then retrieve from database`() = runBlocking {
        defaultPostRetrievalStrategy.retrievePosts(remoteClient, postDao)
        coVerifyOrder {
            remoteClient.getPosts()
            postDao.insert(any())
            postDao.getAllPostsDb()
        }
    }


}