package com.llsparrow.healthassistant.core_ui.view

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.llsparrow.core_network_api.data.exception.Alert
import com.llsparrow.core_network_api.di.CoreNetworkApi
import com.llsparrow.core_network_api.session.UserActivityWatcher
import com.llsparrow.healthassistant.core_di.holder.FeatureUtils
import com.llsparrow.healthassistant.core_feature_toggle_api.config.AppConfig
import com.llsparrow.healthassistant.core_feature_toggle_api.di.CoreFeatureToggleApi
import com.llsparrow.healthassistant.core_ui.error.DialogHelper
import com.llsparrow.healthassistant.core_ui.utils.hideSoftKeyboard

interface BaseActivityCallbacks {
    fun supportPrelogin() = false

    fun onInitialize(savedInstanceState: Bundle?) = Unit

    fun injectDependencies(context: Context) = Unit

    fun releaseDependencies(context: Context) = Unit
}

class BaseActivityDelegate(private val activity: AppCompatActivity) {

    private val baseCallbacks = activity as BaseActivityCallbacks

    private lateinit var userActivityWatcher: UserActivityWatcher
    private lateinit var appConfig: AppConfig

    // private lateinit var localizationApi: LocalizationApi
    private val dialogHelper = DialogHelper()

//    fun setLocale(base: Context): Context {
//        val coreBaseApi = FeatureUtils.getFeature<CoreBaseApi>(base, CoreBaseApi::class.java)
//        localizationApi = coreBaseApi.localizationApi()
//        return localizationApi.wrap(base)
//    }

    //  fun getMenuInflater(): MenuInflater = localizationApi.wrapMenuInflater(activity)

    fun onPreCreate() {
        initBaseDependencies()
        baseCallbacks.injectDependencies(activity.applicationContext)
        if (!appConfig.isDebug()) {
            activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
            )
        }
    }

    fun onPostCreate(savedInstanceState: Bundle?) {
        if (baseCallbacks.supportPrelogin()) {
            baseCallbacks.onInitialize(savedInstanceState)
        } else {
            activity.finish()
        }
    }

    fun onUserInteraction() {
        if (!baseCallbacks.supportPrelogin() && !appConfig.isDebug()) {
            userActivityWatcher.onUserInteracted()
        }
    }

    fun onResume() {
        if (!baseCallbacks.supportPrelogin() && !appConfig.isDebug()) {
            userActivityWatcher.init(activity)
        }
    }

    fun onPause() {
        activity.hideSoftKeyboard()
        if (activity.isFinishing) {
            baseCallbacks.releaseDependencies(activity.applicationContext)
        }
    }

    fun onError(alert: Alert) {
        val supportPrelogin = baseCallbacks.supportPrelogin()
        dialogHelper.showError(activity, alert)
    }

    private fun initBaseDependencies() {
        val coreNetworkApi = FeatureUtils.getFeature<CoreNetworkApi>(activity, CoreNetworkApi::class.java)
        val coreFeatureToggleApi =
            FeatureUtils.getFeature<CoreFeatureToggleApi>(activity, CoreFeatureToggleApi::class.java)

        userActivityWatcher = coreNetworkApi.userActivityWatcher()
        appConfig = coreFeatureToggleApi.appConfig()
    }
}