package com.luckylittlesparrow.healthassistant.appinitializers

import android.app.Application
import com.luckylittlesparrow.core_base_api.appinitializers.AppInitializer
import com.luckylittlesparrow.core_util.logger.DiskLoggerAdapter
import com.luckylittlesparrow.core_util.logger.LoggerAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * @author Gusev Andrei
 * @since  1.0
 */

private const val TAG = "HEALTH_ASSISTANT"

class LoggerInitializer : AppInitializer {
    override fun init(application: Application) {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .tag(TAG)
            .build()

        Logger.addLogAdapter(LoggerAdapter(formatStrategy))
       // Logger.addLogAdapter(DiskLoggerAdapter())
    }
}