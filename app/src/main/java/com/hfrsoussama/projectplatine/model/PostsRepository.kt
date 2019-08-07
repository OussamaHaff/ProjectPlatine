package com.hfrsoussama.projectplatine.model

class PostsRepository {

    private val client = RetrofitClient.webService

    suspend fun getPosts() = client.getPosts()

    suspend fun getUser(userId: Long) = client.getUser(userId)

    suspend fun getCommentsByPostId(postId: Long) = client.getCommentsByPostId(postId)

}