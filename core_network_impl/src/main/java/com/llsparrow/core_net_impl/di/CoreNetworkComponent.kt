package com.llsparrow.core_net_impl.di

import com.llsparrow.core_base_api.di.CoreBaseApi
import com.llsparrow.core_network_api.di.CoreNetworkApi
import com.llsparrow.core_network_api.errors.ErrorHandler
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_feature_toggle_api.di.CoreFeatureToggleApi
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class], dependencies = [CoreNetworkDependencies::class])
@FeatureScope
interface CoreNetworkComponent : CoreNetworkApi {


    @Component(dependencies = [CoreBaseApi::class, CoreFeatureToggleApi::class])
    @FeatureScope
    interface CoreNetworkDependenciesComponent : CoreNetworkDependencies {
        @Component.Builder
        interface Builder {
            fun build(): CoreNetworkDependenciesComponent

            fun coreBaseApi(coreBaseApi: CoreBaseApi): Builder

            fun coreFeatureToggleApi(coreFeatureToggleApi: CoreFeatureToggleApi): Builder
        }
    }
}