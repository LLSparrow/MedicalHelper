package com.oldsenior.ella.corelib.di.injector

import android.app.Activity
import android.arch.lifecycle.LifecycleObserver

internal fun LifecycleObserver.onActivityCreatedEvent(activity: Activity) {
    if (this is Injector<*>) onActivityCreated(activity)
}

internal fun LifecycleObserver.onActivityDestroyedEvent() {
    if (this is Injector<*>) onActivityDestroyed()
}