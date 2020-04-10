package com.llsparrow.healthassistant.core_feature_toggle_impl.di

import com.llsparrow.healthassistant.core_di.holder.FeatureApiHolder
import com.llsparrow.healthassistant.core_di.holder.FeatureContainer
import com.llsparrow.healthassistant.core_feature_toggle_api.config.AppConfig
import com.llsparrow.healthassistant.core_feature_toggle_api.remote.RemoteConfigRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoreFeatureToggleHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val configRepository: RemoteConfigRepository,
    private val appConfig: AppConfig
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        return DaggerCoreFeatureToggleComponent.builder()
            .remoteConfigRepository(configRepository)
            .appConfig(appConfig)
            .build()
    }
}