package com.bouraoui.startwars

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.bouraoui.startwars.di.ApplicationComponent
import com.bouraoui.startwars.di.DaggerApplicationComponent

class MyApplication : Application() , Application.ActivityLifecycleCallbacks , LifecycleObserver {


    companion object {
        var currentActivity: Activity? = null
        lateinit var instance: MyApplication
            private set
    }

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext, currentActivity!!)
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        currentActivity = activity
    }

    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}