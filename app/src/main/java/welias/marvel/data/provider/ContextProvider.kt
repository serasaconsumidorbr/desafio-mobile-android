package welias.marvel.data.provider

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import java.lang.ref.WeakReference

object ContextProvider : Application.ActivityLifecycleCallbacks {

    private var _currentContext: WeakReference<Context>? = null

    val currentContext: Context?
        get() = _currentContext?.get()

    fun initialContext(context: Context) {
        _currentContext = WeakReference(context)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) = Unit

    override fun onActivityStarted(activity: Activity) = Unit

    override fun onActivityResumed(activity: Activity) = Unit

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit

    override fun onActivityDestroyed(activity: Activity) = Unit
}
