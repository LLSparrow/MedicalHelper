package com.llsparrow.healthassistant.core_feature_toggle_api.di

import com.llsparrow.healthassistant.core_feature_toggle_api.config.AppConfig
import com.llsparrow.healthassistant.core_feature_toggle_api.remote.RemoteConfigRepository

interface CoreFeatureToggleApi {
    fun appConfig(): AppConfig
    fun remoteConfig(): RemoteConfigRepository
}