package com.hfrsoussama.projectplatine.feat.posts.core.network

import android.app.Application
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.MediaType
import java.io.InputStreamReader

class MockInterceptor(private val application: Application) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val uri = request.url().uri().toString()
        val response = if (uri.endsWith("/posts")) {
            InputStreamReader(application.assets.open("get_posts.json")).readText()
        } else {
            ""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(response)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"), response.toByteArray()
                )
            )
            .addHeader("content-type", "application/json")
            .build()
    }

}