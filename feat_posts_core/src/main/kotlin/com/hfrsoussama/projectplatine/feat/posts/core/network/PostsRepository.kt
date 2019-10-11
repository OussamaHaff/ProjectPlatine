package com.hfrsoussama.projectplatine.feat.posts.core.network

import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.CommentUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.PostUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.UserUi

interface PostsRepository {

    suspend fun getPosts() : List<PostUi>

    suspend fun getUser(userId: Long) : UserUi

    suspend fun getCommentsByPostId(postId: Long): List<CommentUi>
}