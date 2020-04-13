package com.llsparrow.healthassistant.common_navigation.di

import com.llsparrow.healthassistant.common_navigation_api.navigation.core.NavigationController
import com.llsparrow.healthassistant.core_di.holder.FeatureApiHolder
import com.llsparrow.healthassistant.core_di.holder.FeatureContainer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationCommonHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val navigationController: NavigationController
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val navigationCommonDependencies =
            DaggerNavigationCommonComponent_NavigationCommonDependenciesComponent.builder()
                .navigationController(navigationController)
                .build()
        return DaggerNavigationCommonComponent.builder()
            .navigationCommonDependencies(navigationCommonDependencies)
            .build()
    }
}
