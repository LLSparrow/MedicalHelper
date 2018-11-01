package com.oldsenior.ella.corelib.di.injector

import android.app.Activity
import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.os.Bundle
import com.oldsenior.ella.corelib.di.component.AppComponent
import com.oldsenior.ella.corelib.di.component.BaseComponent
import com.oldsenior.ella.corelib.di.component.DaggerAppComponent
import com.oldsenior.ella.corelib.view.CoreApplication

object AppInjector : Lifecycle() {

    lateinit var baseComponent: BaseComponent

    private lateinit var appComponent: AppComponent
    private val subscribers = ArrayList<LifecycleObserver>()

    override fun addObserver(observer: LifecycleObserver) {
        subscribers.add(observer)
    }

    override fun removeObserver(observer: LifecycleObserver) {
        subscribers.remove(observer)
    }

    override fun getCurrentState(): State = State.RESUMED

    fun init(application: CoreApplication) {
        appComponent = DaggerAppComponent.builder().applicationContext(application)
                .build()
        baseComponent = appComponent.baseSubcomponentBuilder().build()

        application
                .registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                        subscribers.forEach { it.onActivityCreatedEvent(activity) }
                    }

                    override fun onActivityStarted(activity: Activity) {

                    }

                    override fun onActivityResumed(activity: Activity) {

                    }

                    override fun onActivityPaused(activity: Activity) {

                    }

                    override fun onActivityStopped(activity: Activity) {

                    }

                    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {

                    }

                    override fun onActivityDestroyed(activity: Activity) {
                        subscribers.forEach { it.onActivityDestroyedEvent() }
                    }
                })
    }
}
