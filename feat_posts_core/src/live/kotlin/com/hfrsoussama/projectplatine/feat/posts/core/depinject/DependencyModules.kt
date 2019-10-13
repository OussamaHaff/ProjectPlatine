package com.hfrsoussama.projectplatine.feat.posts.core.depinject

import com.hfrsoussama.projectplatine.feat.posts.core.network.DebugInterceptor
import com.hfrsoussama.projectplatine.feat.posts.core.viewmodel.MainViewModel
import okhttp3.Interceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val PostsNetworkModule = module {

    single { DebugInterceptor() as Interceptor }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { providePostsService(get()) }
    single { providePostsRepository(get()) }
    viewModel { MainViewModel(get()) }

}