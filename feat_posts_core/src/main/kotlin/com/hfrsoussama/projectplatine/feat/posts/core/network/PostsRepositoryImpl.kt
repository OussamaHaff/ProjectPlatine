package com.hfrsoussama.projectplatine.feat.posts.core.network

import com.hfrsoussama.projectplatine.feat.posts.core.model.extensions.toUiModel
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.CommentUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.PostUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.CommentWs
import com.hfrsoussama.projectplatine.shared.database.dao.PostDao

class PostsRepositoryImpl(
    private val client: PostsWebServices,
    private val postDao: PostDao
) : PostsRepository {

    override suspend fun getPosts(strategy: PostRetrievalStrategy): List<PostUi> =
        strategy.retrievePosts(client, postDao)


    override suspend fun getUser(userId: Long) = client.getUser(userId).toUiModel()

    override suspend fun getCommentsByPostId(postId: Long): List<CommentUi> {
        return client.getCommentsByPostId(postId).map(CommentWs::toUiModel)
    }



}
