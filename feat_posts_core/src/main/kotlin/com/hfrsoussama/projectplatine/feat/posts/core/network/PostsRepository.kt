package com.hfrsoussama.projectplatine.feat.posts.core.network

import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.PostUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.UserUi

interface PostsRepository {

    suspend fun getPosts(strategy: PostRetrievalStrategy = DefaultPostRetrievalStrategy()) : List<PostUi>

    suspend fun getUser(userId: Long) : UserUi

}
