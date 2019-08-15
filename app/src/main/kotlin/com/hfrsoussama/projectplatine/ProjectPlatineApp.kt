package com.hfrsoussama.projectplatine

import android.app.Application
import timber.log.Timber

class ProjectPlatineApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}