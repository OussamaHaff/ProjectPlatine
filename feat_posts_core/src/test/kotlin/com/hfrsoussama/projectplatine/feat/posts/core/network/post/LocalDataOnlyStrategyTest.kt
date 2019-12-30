package com.hfrsoussama.projectplatine.feat.posts.core.network.post

import com.hfrsoussama.projectplatine.feat.posts.core.network.LocalPostDataOnlyStrategy
import com.hfrsoussama.projectplatine.feat.posts.core.network.PostsWebServices
import com.hfrsoussama.projectplatine.shared.database.dao.PostDao
import com.hfrsoussama.projectplatine.shared.database.entities.PostDb
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LocalDataOnlyStrategyTest {

    private val remoteClient = mockk<PostsWebServices>()
    private val postDao = mockk<PostDao>()

    private val localPostDataOnlyStrategy = LocalPostDataOnlyStrategy()

    @Before
    fun createStubs() {
        coEvery {
            postDao.getAllPostsDb()
        } returns listOf(
            PostDb(
                postId = 1L,
                userWriterId = 1L,
                title = "post title",
                body = "post body"
            )
        )

    }

    @Test
    fun `should call only local database`() = runBlocking {
        localPostDataOnlyStrategy.retrievePosts(remoteClient, postDao)
        coVerify {
            remoteClient wasNot Called
        }

    }
}