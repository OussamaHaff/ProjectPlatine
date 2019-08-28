package com.hfrsoussama.projectplatine.feat.posts.core.network

import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.CommentWs
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.PostWs
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.UserWs
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET("/posts")
    suspend fun getPosts() : List<PostWs>

    @GET("/users/{userId}")
    suspend fun getUser(@Path("userId") userId: Long): UserWs

    @GET("/comments")
    suspend fun getCommentsByPostId(@Query("postId") postId: Long) : List<CommentWs>
}
