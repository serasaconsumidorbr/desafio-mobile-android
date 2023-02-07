package com.desafio.android.core.base

import android.app.Activity
import android.app.Application
import com.desafio.android.core.extension.setActivityListener
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    var activity: Activity? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

        setActivityListener(
            onCreated = {
                if (activity == null) activity = it
            }
        )
    }

    companion object {
        lateinit var instance: BaseApplication private set
    }
}