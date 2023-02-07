package com.desafio.android.core.extension

import android.app.Activity
import android.app.Application
import android.os.Bundle

fun Application.setActivityListener(
    onCreated: ((Activity) -> Unit)? = null,
    onStarted: ((Activity) -> Unit)? = null,
    onResumed: ((Activity) -> Unit)? = null,
    onPaused: ((Activity) -> Unit)? = null,
    onStopped: ((Activity) -> Unit)? = null,
    onSaveInstanceState: ((Activity) -> Unit)? = null,
    onDestroyed: ((Activity) -> Unit)? = null,
) {
    registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(p0: Activity, p1: Bundle?) {
            onCreated?.invoke(p0)
        }

        override fun onActivityStarted(p0: Activity) {
            onStarted?.invoke(p0)
        }

        override fun onActivityResumed(p0: Activity) {
            onResumed?.invoke(p0)
        }

        override fun onActivityPaused(p0: Activity) {
            onPaused?.invoke(p0)
        }

        override fun onActivityStopped(p0: Activity) {
            onStopped?.invoke(p0)
        }

        override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            onSaveInstanceState?.invoke(p0)
        }

        override fun onActivityDestroyed(p0: Activity) {
            onDestroyed?.invoke(p0)
        }
    })
}