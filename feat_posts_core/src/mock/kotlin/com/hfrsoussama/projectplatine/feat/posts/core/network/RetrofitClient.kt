package com.hfrsoussama.projectplatine.feat.posts.core.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object RetrofitClient {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(LoggingInterceptor())
        .build()

    val webService: WebService by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }

}


class LoggingInterceptor: Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        with(request) {
            Timber.d(
                "Request for Url ${this.url()}\n" +
                        "of method ${this.method()}\n" +
                        "with body ${this.body()}\n" +
                        "with headers ${this.headers()}"
            )
        }
        return  chain.proceed(request)
    }

}