package com.llsparrow.healthassistant.appinitializers

import android.app.Application
import com.llsparrow.core_base_api.appinitializers.AppInitializer
import com.llsparrow.core_util.logger.LoggerAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "HEALTH_ASSISTANT"

@Singleton
class LoggerInitializer @Inject constructor() : AppInitializer {
    override fun init(application: Application) {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .tag(TAG)
            .build()

        Logger.addLogAdapter(LoggerAdapter(formatStrategy))
        // Logger.addLogAdapter(DiskLoggerAdapter())
    }
}