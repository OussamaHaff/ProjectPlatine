package com.hfrsoussama.projectplatine.feat.posts.core.depinject

import com.hfrsoussama.projectplatine.feat.posts.core.network.LoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val PostsNetworkModule = module {

    single { LoggingInterceptor(androidApplication()) }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { providePostsService(get()) }
    single { providePostsRepository(get()) }

}