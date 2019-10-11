package com.hfrsoussama.projectplatine.feat.posts.core.network

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.PostWs
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.InputStreamReader

class LoggingInterceptor(private val application: Application) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        with(request) {
            val isReader = InputStreamReader(application.assets.open("get_posts.json"))
            val posts = Gson().toJson(isReader, object : TypeToken<List<PostWs>>() {}.type)
            Timber.d(
                "Request for Url ${this.url()}\n" +
                        "of method ${this.method()}\n" +
                        "with body ${this.body()}\n" +
                        "with headers ${this.headers()}\n" +
                        "posts are here : \n" +
                        "$posts"
            )
        }
        return chain.proceed(request)
    }

}