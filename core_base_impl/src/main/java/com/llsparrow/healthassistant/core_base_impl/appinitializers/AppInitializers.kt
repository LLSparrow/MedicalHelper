package com.llsparrow.healthassistant.core_base_impl.appinitializers

import android.app.Application
import com.llsparrow.core_base_api.appinitializers.AppInitializer
import javax.inject.Inject

class AppInitializers @Inject constructor(
    private val initializers: Set<@JvmSuppressWildcards AppInitializer>
) {
    fun init(application: Application) {
        initializers.forEach {
            it.init(application)
        }
    }
}