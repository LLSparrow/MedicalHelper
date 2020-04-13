package com.llsparrow.healthassistant.common_navigation.di

import com.llsparrow.healthassistant.common_navigation_api.navigation.core.NavigationController
import com.llsparrow.healthassistant.common_navigation_api.navigation.di.NavigationCommonApi
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [NavigationCommonDependencies::class],
    modules = [
        NavigationCommonModule::class
    ]
)
@Singleton
abstract class NavigationCommonComponent : NavigationCommonApi {

    @Component
    @Singleton
    internal interface NavigationCommonDependenciesComponent : NavigationCommonDependencies {
        @Component.Builder
        interface Builder {
            fun build(): NavigationCommonDependenciesComponent
            @BindsInstance
            fun navigationController(navigationController: NavigationController): Builder
        }
    }
}