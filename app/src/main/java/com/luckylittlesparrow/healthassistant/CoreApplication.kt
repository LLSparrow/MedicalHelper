package com.luckylittlesparrow.healthassistant

import android.os.StrictMode
import androidx.multidex.MultiDexApplication
import com.luckylittlesparrow.core_util.logger.DiskLoggerAdapter
import com.luckylittlesparrow.core_util.logger.LoggerAdapter
import com.luckylittlesparrow.healthassistant.appinitializers.KoinInitializer
import com.luckylittlesparrow.healthassistant.core_base_impl.appinitializers.AppInitializers
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import kotlinx.coroutines.DEBUG_PROPERTY_NAME
import kotlinx.coroutines.DEBUG_PROPERTY_VALUE_ON
import org.koin.android.ext.android.inject

private const val TAG = "HEALTH_ASSISTANT"

class CoreApplication : MultiDexApplication() {

    private val appInitializer: AppInitializers by inject()

    init {
        System.setProperty(
            DEBUG_PROPERTY_NAME,
            DEBUG_PROPERTY_VALUE_ON
        )
    }

    override fun onCreate() {
        if (BuildConfig.DEBUG) {
            enableStrictMode()
        }
      //  AppWatcher.config = AppWatcher.config.copy(enabled = false)


        super.onCreate()

        KoinInitializer().init(this)
        //appInitializer.init(this)
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .tag(TAG)
            .build()

        Logger.addLogAdapter(LoggerAdapter(formatStrategy))
        Logger.addLogAdapter(DiskLoggerAdapter())
    }



    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build()
        )
    }
}