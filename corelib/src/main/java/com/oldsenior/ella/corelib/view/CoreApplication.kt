package com.oldsenior.ella.corelib.view

import android.app.Application
import com.oldsenior.ella.corelib.BuildConfig
import com.oldsenior.ella.corelib.di.injector.AppInjector
import timber.log.Timber

class CoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        AppInjector.init(this)
    }
}