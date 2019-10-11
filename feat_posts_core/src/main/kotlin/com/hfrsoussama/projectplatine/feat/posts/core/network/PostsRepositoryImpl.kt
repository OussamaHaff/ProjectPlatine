package com.hfrsoussama.projectplatine.feat.posts.core.network

import com.hfrsoussama.projectplatine.feat.posts.core.model.extensions.toUiModel
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.CommentUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.CommentWs
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.PostWs

class PostsRepositoryImpl(private val client: PostsWebServices) : PostsRepository {

    override suspend fun getPosts() = client.getPosts().map(PostWs::toUiModel)

    override suspend fun getUser(userId: Long) = client.getUser(userId).toUiModel()

    override suspend fun getCommentsByPostId(postId: Long): List<CommentUi> {
        return client.getCommentsByPostId(postId).map(CommentWs::toUiModel)
    }



}
