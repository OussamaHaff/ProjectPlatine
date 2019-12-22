package com.hfrsoussama.projectplatine.feat.posts.core.depinject

import com.hfrsoussama.projectplatine.feat.posts.core.network.MockInterceptor
import com.hfrsoussama.projectplatine.feat.posts.core.viewmodel.MainViewModel
import okhttp3.Interceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val PostsNetworkModule = module {

    single { MockInterceptor(androidApplication().assets) as Interceptor}
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideDatabase(context = get()).postDao() }
    single { providePostsService(get()) }
    factory { providePostsRepository(postsWebServices = get(), postDao = get()) }
    viewModel { MainViewModel(get()) }

}