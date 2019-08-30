package com.hysyyl.bloodapp

import android.app.Activity
import android.os.Bundle
import com.lpjeremy.uimodule.BaseApplication

class BloodApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        registerToolbarActivityLifecycleCallback()

    }
    fun registerToolbarActivityLifecycleCallback(){
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks{
            override fun onActivityPaused(p0: Activity?) {

            }

            override fun onActivityResumed(p0: Activity?) {
            }

            override fun onActivityStarted(p0: Activity?) {
            }

            override fun onActivityDestroyed(p0: Activity?) {
            }

            override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
            }

            override fun onActivityStopped(p0: Activity?) {
            }

            override fun onActivityCreated(activity: Activity?, bundule: Bundle?) {
            }
        })
    }
}