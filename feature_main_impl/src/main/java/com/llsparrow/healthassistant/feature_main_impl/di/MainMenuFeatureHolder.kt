package com.llsparrow.healthassistant.feature_main_impl.di

import com.llsparrow.core_base_api.di.CoreBaseApi
import com.llsparrow.healthassistant.core_di.holder.FeatureApiHolder
import com.llsparrow.healthassistant.core_di.holder.FeatureContainer
import com.llsparrow.healthassistant.feature_authentication_api.di.AuthenticationFeatureApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainMenuFeatureHolder @Inject constructor(featureContainer: FeatureContainer) :
    FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val mainMenuFeatureDependencies =
            DaggerMainMenuFeatureComponent_AuthenticationFeatureDependenciesComponent.builder()
                .coreBaseApi(getFeature(CoreBaseApi::class.java))
                .authenticationFeatureApi(getFeature(AuthenticationFeatureApi::class.java))
                .build()
        return DaggerMainMenuFeatureComponent.builder()
            .mainMenuFeatureDependencies(mainMenuFeatureDependencies)
            .build()
    }
}
