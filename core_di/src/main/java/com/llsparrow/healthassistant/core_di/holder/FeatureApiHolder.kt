package com.llsparrow.healthassistant.core_di.holder

import com.orhanobut.logger.Logger
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

abstract class FeatureApiHolder(private val featureContainer: FeatureContainer) {

    private val featureLocker: Lock = ReentrantLock()

    private var featureApi: Any? = null

    fun <T> getFeatureApi(): T {
        featureLocker.lock()
        if (featureApi == null) {
            Logger.d("init: $javaClass")
            featureApi = initializeDependencies()
        }
        featureLocker.unlock()
        return featureApi as T
    }

    fun releaseFeatureApi() {
        featureLocker.lock()
        Logger.d("release: $javaClass")
        featureApi = null
        destroyDependencies()
        featureLocker.unlock()
    }

    protected fun <T> getFeature(key: Class<T>): T {
        return featureContainer.getFeature(key)
    }

    protected fun releaseFeature(key: Class<*>) {
        featureContainer.releaseFeature(key)
    }

    protected abstract fun initializeDependencies(): Any

    protected fun destroyDependencies() {}
}