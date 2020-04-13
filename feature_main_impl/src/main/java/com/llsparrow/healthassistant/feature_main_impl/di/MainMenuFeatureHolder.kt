package com.llsparrow.healthassistant.feature_main_impl.di

import com.llsparrow.healthassistant.common_navigation_api.navigation.di.NavigationCommonApi
import com.llsparrow.core_base_api.di.CoreBaseApi
import com.llsparrow.healthassistant.core_di.holder.FeatureApiHolder
import com.llsparrow.healthassistant.core_di.holder.FeatureContainer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainMenuFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val mainMenuFeatureDependencies =
            DaggerMainMenuFeatureComponent_MainMenuFeatureDependenciesComponent.builder()
                .coreBaseApi(getFeature(CoreBaseApi::class.java))
                .navigationCommonApi(getFeature(NavigationCommonApi::class.java))
                .build()
        return DaggerMainMenuFeatureComponent.builder()
            .mainMenuFeatureDependencies(mainMenuFeatureDependencies)
            .build()
    }
}
