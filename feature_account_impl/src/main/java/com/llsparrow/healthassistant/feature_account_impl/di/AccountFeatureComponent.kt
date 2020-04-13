package com.llsparrow.healthassistant.feature_account_impl.di

import com.llsparrow.core_base_api.di.CoreBaseApi
import com.llsparrow.core_network_api.di.CoreNetworkApi
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_account_api.di.AccountFeatureApi
import com.llsparrow.healthassistant.feature_secure_storage_api.di.SecureStorageFeatureApi
import dagger.Component

@Component(
    dependencies = [AccountFeatureDependencies::class],
    modules = [
        AccountFeatureModule::class
    ]
)
@FeatureScope
abstract class AccountFeatureComponent : AccountFeatureApi {

    @Component(
        dependencies = [
            CoreNetworkApi::class,
            CoreBaseApi::class,
            SecureStorageFeatureApi::class
        ]
    )
    @FeatureScope
    internal interface AccountFeatureDependenciesComponent : AccountFeatureDependencies
}