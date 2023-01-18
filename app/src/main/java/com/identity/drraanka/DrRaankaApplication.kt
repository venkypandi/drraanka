package com.identity.drraanka

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DrRaankaApplication: Application() {

    companion object {
        var instance: DrRaankaApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}