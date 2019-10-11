package com.hfrsoussama.projectplatine.feat.posts.core.depinject

import com.google.gson.GsonBuilder
import com.hfrsoussama.projectplatine.feat.posts.core.network.PostsRepositoryImpl
import com.hfrsoussama.projectplatine.feat.posts.core.network.PostsWebServices
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .build()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    .build()

fun providePostsService(retrofit: Retrofit): PostsWebServices =
    retrofit.create(PostsWebServices::class.java)

fun providePostsRepository(postsWebServices: PostsWebServices) = PostsRepositoryImpl(postsWebServices)
