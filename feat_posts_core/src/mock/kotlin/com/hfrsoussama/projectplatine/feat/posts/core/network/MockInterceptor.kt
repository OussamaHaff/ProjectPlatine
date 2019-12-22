package com.hfrsoussama.projectplatine.feat.posts.core.network

import android.content.res.AssetManager
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.MediaType
import java.io.InputStreamReader

class MockInterceptor(private val assetManager: AssetManager) : Interceptor {

    private companion object {
        const val HTTP_SUCCESS_CODE = 200
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val uri = request.url().uri().toString()
        val mockResponse = when {
            uri.endsWith("/posts") -> InputStreamReader(assetManager.open("get_posts.json")).readText()
            uri.contains("/users/") -> InputStreamReader(assetManager.open("get_user.json")).readText()
            uri.contains("/comments") -> InputStreamReader(assetManager.open("get_comments.json")).readText()
            else -> ""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(HTTP_SUCCESS_CODE)
            .protocol(Protocol.HTTP_2)
            .message(mockResponse)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"), mockResponse.toByteArray()
                )
            )
            .addHeader("content-type", "application/json")
            .build()

    }

}
