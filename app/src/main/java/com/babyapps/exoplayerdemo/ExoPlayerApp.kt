package com.babyapps.exoplayerdemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ExoPlayerApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}