package com.llsparrow.healthassistant.feature_account_impl.di

import com.llsparrow.core_base_api.di.CoreBaseApi
import com.llsparrow.core_network_api.di.CoreNetworkApi
import com.llsparrow.healthassistant.core_di.holder.FeatureApiHolder
import com.llsparrow.healthassistant.core_di.holder.FeatureContainer
import com.llsparrow.healthassistant.feature_secure_storage_api.di.SecureStorageFeatureApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val accountFeatureDependencies =
            DaggerAccountFeatureComponent_AccountFeatureDependenciesComponent.builder()
                .coreNetworkApi(getFeature(CoreNetworkApi::class.java))
                .coreBaseApi(getFeature(CoreBaseApi::class.java))
                .secureStorageFeatureApi(getFeature(SecureStorageFeatureApi::class.java))
                .build()
        return DaggerAccountFeatureComponent.builder()
            .accountFeatureDependencies(accountFeatureDependencies)
            .build()
    }
}
