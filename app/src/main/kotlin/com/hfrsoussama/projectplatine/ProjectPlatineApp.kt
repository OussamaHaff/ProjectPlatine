package com.hfrsoussama.projectplatine

import android.app.Application
import com.hfrsoussama.projectplatine.feat.posts.core.depinject.PostsNetworkModule
import com.hfrsoussama.projectplatine.feat.posts.core.network.PostsRepository
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class ProjectPlatineApp : Application() {


    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }




        startKoin {
            androidContext(this@ProjectPlatineApp)
            modules(PostsNetworkModule)
        }




    }



}