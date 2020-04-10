package com.llsparrow.healthassistant

import android.os.StrictMode
import android.webkit.WebView
import androidx.multidex.MultiDexApplication
import com.llsparrow.healthassistant.core_base_impl.appinitializers.AppInitializers
import com.llsparrow.healthassistant.core_di.holder.FeatureContainer
import com.llsparrow.healthassistant.core_di.holder.FeatureHolderManager
import com.llsparrow.healthassistant.di.AppComponent
import com.llsparrow.healthassistant.di.DaggerAppComponent
import kotlinx.coroutines.DEBUG_PROPERTY_NAME
import kotlinx.coroutines.DEBUG_PROPERTY_VALUE_ON
import javax.inject.Inject

class CoreApplication : MultiDexApplication(), FeatureContainer {

    @Inject
    lateinit var initializers: AppInitializers

    @Inject
    lateinit var featureHolderManager: FeatureHolderManager

    init {
        System.setProperty(
            DEBUG_PROPERTY_NAME,
            DEBUG_PROPERTY_VALUE_ON
        )
    }

    override fun onCreate() {
//        if (BuildConfig.DEBUG) {
//            enableStrictMode()
//        }

        WebView(this).destroy() // fix language selection bug

        AppComponent.init(
            DaggerAppComponent.builder()
                .application(this)
                .build()
        )

        AppComponent.sInstance!!.inject(this)


        //  AppWatcher.config = AppWatcher.config.copy(enabled = false)


        super.onCreate()

        initializers.init(this)
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

    override fun <T> getFeature(key: Class<*>): T {
        return featureHolderManager.getFeature(key)
    }

    override fun releaseFeature(key: Class<*>) {
        return featureHolderManager.releaseFeature(key)
    }
}