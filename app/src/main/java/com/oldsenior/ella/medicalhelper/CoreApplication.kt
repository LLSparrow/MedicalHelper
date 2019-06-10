package com.oldsenior.ella.medicalhelper

import android.app.Application
import com.oldsenior.core_util.logger.DiskLoggerAdapter
import com.oldsenior.core_util.logger.LoggerAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

class CoreApplication : Application() {
    companion object {
        private const val TAG = "BAKONG"
    }

    override fun onCreate() {
        super.onCreate()
        initLogger()
        // AppInjector.init(this)
    }

    private fun initLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .tag(TAG)
            .build()

        Logger.addLogAdapter(LoggerAdapter(formatStrategy))
        Logger.addLogAdapter(DiskLoggerAdapter())
    }
}