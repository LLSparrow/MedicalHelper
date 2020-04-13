package com.llsparrow.healthassistant.feature_authentication_impl.di

import com.llsparrow.core_base_api.di.CoreBaseApi
import com.llsparrow.core_network_api.di.CoreNetworkApi
import com.llsparrow.healthassistant.common_navigation_api.navigation.di.NavigationCommonApi
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_account_api.di.AccountFeatureApi
import com.llsparrow.healthassistant.feature_authentication_api.di.AuthenticationFeatureApi
import com.llsparrow.healthassistant.feature_authentication_impl.presentation.signin.SignInFragment
import com.llsparrow.healthassistant.feature_authentication_impl.presentation.signup.SignUpFragment
import dagger.Component

@Component(
    dependencies = [AuthenticationFeatureDependencies::class],
    modules = [
        AuthenticationFeatureModule::class,
        AuthenticationViewModelModule::class
    ]
)
@FeatureScope
abstract class AuthenticationFeatureComponent : AuthenticationFeatureApi {
    abstract fun inject(fragment: SignUpFragment)
    abstract fun inject(fragment: SignInFragment)

    @Component(
        dependencies = [
            CoreNetworkApi::class,
            CoreBaseApi::class,
            NavigationCommonApi::class,
            AccountFeatureApi::class
        ]
    )
    @FeatureScope
    internal interface AuthenticationFeatureDependenciesComponent : AuthenticationFeatureDependencies
}