package com.hfrsoussama.projectplatine.feat.posts.core.depinject

import com.google.gson.GsonBuilder
import com.hfrsoussama.projectplatine.feat.posts.core.network.PostsRepository
import com.hfrsoussama.projectplatine.feat.posts.core.network.PostsWebServices
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    .build()

fun providePostsService(retrofit: Retrofit): PostsWebServices =
    retrofit.create(PostsWebServices::class.java)

fun providePostsRepository(postsWebServices: PostsWebServices) = PostsRepository(postsWebServices)
