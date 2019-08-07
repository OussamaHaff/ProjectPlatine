package com.hfrsoussama.projectplatine.model

class PostsRepository {

    private val client = RetrofitClient.webService

    suspend fun getPosts() = client.getPosts()

}