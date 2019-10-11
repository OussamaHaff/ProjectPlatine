package com.hfrsoussama.projectplatine.feat.posts.core.depinject

import com.hfrsoussama.projectplatine.feat.posts.core.network.LoggingInterceptor
import com.hfrsoussama.projectplatine.feat.posts.core.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val PostsNetworkModule = module {

    single { LoggingInterceptor(androidApplication()) }
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { providePostsService(get()) }
    single { providePostsRepository(get()) }
    viewModel { MainViewModel(get()) }

}