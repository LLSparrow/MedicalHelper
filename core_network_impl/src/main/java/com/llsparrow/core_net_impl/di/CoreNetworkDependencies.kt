package com.llsparrow.core_net_impl.di

import android.content.Context
import com.llsparrow.core_base_api.date.TimeSource
import com.llsparrow.core_base_api.resources.ResourceManager
import com.llsparrow.core_network_api.data.HostnameProvider
import com.llsparrow.core_network_api.errors.ErrorHandler
import com.llsparrow.healthassistant.core_feature_toggle_api.config.AppConfig

interface CoreNetworkDependencies {
    fun context(): Context

    fun appConfig(): AppConfig

    fun resourceManager(): ResourceManager

    fun timeSource(): TimeSource
}