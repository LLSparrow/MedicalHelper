package com.luckylittlesparrow.healthassistant.core_base_impl.appinitializers

import android.app.Application
import com.luckylittlesparrow.core_base_api.appinitializers.AppInitializer

class AppInitializers constructor(
    private val initializers: Set<@JvmSuppressWildcards AppInitializer>
) {
    fun init(application: Application) {
        initializers.forEach {
            it.init(application)
        }
    }
}