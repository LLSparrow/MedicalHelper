package com.llsparrow.healthassistant.feature_main_impl.di

import com.llsparrow.core_base_api.di.CoreBaseApi
import com.llsparrow.feature_main_api.di.MainMenuFeatureApi
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_authentication_api.di.AuthenticationFeatureApi
import com.llsparrow.healthassistant.feature_main_impl.MainMenuFragment
import dagger.Component

@Component(
    dependencies = [MainMenuFeatureDependencies::class],
    modules = [
        MainMenuFeatureModule::class,
        MainMenuViewModelModule::class,
        MainMenuWizardModule::class
    ]
)
@FeatureScope
abstract class MainMenuFeatureComponent : MainMenuFeatureApi {
    abstract fun inject(fragment: MainMenuFragment)

    @Component(
        dependencies = [
            CoreBaseApi::class,
            AuthenticationFeatureApi::class
        ]
    )
    @FeatureScope
    internal interface AuthenticationFeatureDependenciesComponent : MainMenuFeatureDependencies
}