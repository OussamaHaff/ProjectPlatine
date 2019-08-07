package com.hfrsoussama.projectplatine.model

import retrofit2.http.GET

interface WebService {

    @GET("/posts")
    suspend fun getPosts() : List<Post>
}