package com.hfrsoussama.projectplatine.feat.posts.core.network

import com.hfrsoussama.projectplatine.feat.posts.core.model.Comment
import com.hfrsoussama.projectplatine.feat.posts.core.model.Post
import com.hfrsoussama.projectplatine.feat.posts.core.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET("/posts")
    suspend fun getPosts() : List<Post>

    @GET("/users/{userId}")
    suspend fun getUser(@Path("userId") userId: Long): User

    @GET("/comments")
    suspend fun getCommentsByPostId(@Query("postId") postId: Long) : List<Comment>
}
