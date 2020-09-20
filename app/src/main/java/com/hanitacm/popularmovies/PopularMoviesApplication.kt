package com.hanitacm.popularmovies

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PopularMoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}