package com.hfrsoussama.projectplatine.feat.posts.core.network

import com.hfrsoussama.projectplatine.feat.posts.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class DebugInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (BuildConfig.DEBUG) {
            with(request) {
                Timber.d(
                    "Request for Url ${this.url()}\n" +
                            "of method ${this.method()}\n" +
                            "with body ${this.body().toString()}\n" +
                            "with headers ${this.headers()}\n"
                )
            }
            with(response) {
                Timber.d(
                    "Response Code ${this.code()}\n" +
                            "was redirected ${this.isRedirect}\n" +
                            "with body ${this.body().toString()}\n" +
                            "with headers ${this.headers()}\n"
                )
            }
        }
        return response
    }



}
