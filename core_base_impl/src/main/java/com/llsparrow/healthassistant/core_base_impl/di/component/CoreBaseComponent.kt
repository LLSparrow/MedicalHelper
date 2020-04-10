package com.llsparrow.healthassistant.core_base_impl.di.component

import android.content.Context
import com.llsparrow.core_base_api.di.CoreBaseApi
import com.llsparrow.core_base_api.navigation.NavigationController
import com.llsparrow.healthassistant.core_base_impl.di.CoreBaseModule
import com.llsparrow.healthassistant.core_feature_toggle_api.di.CoreFeatureToggleApi
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(dependencies = [CoreFeatureToggleApi::class], modules = [CoreBaseModule::class])
@Singleton
abstract class CoreBaseComponent : CoreBaseApi {
    @Component.Builder
    interface Builder {
        fun build(): CoreBaseComponent

        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun navigationController(navigationController: NavigationController): Builder

        fun coreFeatureToggleApi(coreFeatureToggleApi: CoreFeatureToggleApi): Builder
    }
}