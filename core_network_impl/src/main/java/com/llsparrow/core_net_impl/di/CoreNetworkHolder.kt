package com.llsparrow.core_net_impl.di

import com.llsparrow.core_base_api.di.CoreBaseApi
import com.llsparrow.core_network_api.errors.ErrorHandler
import com.llsparrow.healthassistant.core_di.holder.FeatureApiHolder
import com.llsparrow.healthassistant.core_di.holder.FeatureContainer
import com.llsparrow.healthassistant.core_feature_toggle_api.di.CoreFeatureToggleApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoreNetworkHolder @Inject constructor(
    featureContainer: FeatureContainer
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val coreNetworkDependencies: CoreNetworkDependencies =
            DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder()
                .coreBaseApi(getFeature(CoreBaseApi::class.java))
                .coreFeatureToggleApi(getFeature(CoreFeatureToggleApi::class.java))
                .build()
        return DaggerCoreNetworkComponent.builder()
            .coreNetworkDependencies(coreNetworkDependencies)
            .build()
    }

}