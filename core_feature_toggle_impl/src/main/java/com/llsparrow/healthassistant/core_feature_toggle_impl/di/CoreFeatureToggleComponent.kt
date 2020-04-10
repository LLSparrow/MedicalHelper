package com.llsparrow.healthassistant.core_feature_toggle_impl.di

import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_feature_toggle_api.config.AppConfig
import com.llsparrow.healthassistant.core_feature_toggle_api.di.CoreFeatureToggleApi
import com.llsparrow.healthassistant.core_feature_toggle_api.remote.RemoteConfigRepository
import dagger.BindsInstance
import dagger.Component

@Component
@FeatureScope
interface CoreFeatureToggleComponent : CoreFeatureToggleApi {
    @Component.Builder
    interface Builder {
        fun build(): CoreFeatureToggleComponent

        @BindsInstance
        fun remoteConfigRepository(remoteConfigRepository: RemoteConfigRepository): Builder

        @BindsInstance
        fun appConfig(appConfig: AppConfig): Builder
    }
}