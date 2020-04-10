package com.llsparrow.healthassistant.config.local

import android.content.Context
import com.llsparrow.healthassistant.BuildConfig
import com.llsparrow.healthassistant.R
import com.llsparrow.healthassistant.core_feature_toggle_api.config.AppConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppBuildConfig @Inject constructor(
    private val context: Context
) : AppConfig {
    override fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun isDebugBuildType(): Boolean {
        return BuildConfig.BUILD_TYPE == "debug"
    }

    override fun getPackage(): String {
        return BuildConfig.APPLICATION_ID
    }

    override fun getVersionCode(): Int {
        return BuildConfig.VERSION_CODE
    }

    override fun getVersionName(): String {
        return BuildConfig.VERSION_NAME
    }

    override fun apiVersion(): String {
        return BuildConfig.API_VERSION
    }

    override fun appName(): String {
        return context.getString(R.string.app_name)
    }
}