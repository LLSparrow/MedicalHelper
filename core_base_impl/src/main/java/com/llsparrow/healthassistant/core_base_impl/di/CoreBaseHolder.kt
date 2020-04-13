package com.llsparrow.healthassistant.core_base_impl.di

import android.content.Context
import com.llsparrow.healthassistant.core_base_impl.di.component.DaggerCoreBaseComponent
import com.llsparrow.healthassistant.core_di.holder.FeatureApiHolder
import com.llsparrow.healthassistant.core_di.holder.FeatureContainer
import com.llsparrow.healthassistant.core_feature_toggle_api.di.CoreFeatureToggleApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoreBaseHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val applicationContext: Context
) :
    FeatureApiHolder(featureContainer) {
    override fun initializeDependencies(): Any {
        return DaggerCoreBaseComponent.builder()
            .context(applicationContext)
            .coreFeatureToggleApi(getFeature(CoreFeatureToggleApi::class.java))
            .build()
    }
}