package com.llsparrow.healthassistant.feature_authentication_impl.di

import com.llsparrow.core_base_api.di.CoreBaseApi
import com.llsparrow.core_network_api.di.CoreNetworkApi
import com.llsparrow.healthassistant.core_di.holder.FeatureApiHolder
import com.llsparrow.healthassistant.core_di.holder.FeatureContainer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationFeatureHolder @Inject constructor(featureContainer: FeatureContainer) :
    FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val authenticationFeatureDependencies =
            DaggerAuthenticationFeatureComponent_AuthenticationFeatureDependenciesComponent.builder()
                .coreNetworkApi(getFeature(CoreNetworkApi::class.java))
                .coreBaseApi(getFeature(CoreBaseApi::class.java))
                .build()
        return DaggerAuthenticationFeatureComponent.builder()
            .authenticationFeatureDependencies(authenticationFeatureDependencies)
            .build()
    }
}
