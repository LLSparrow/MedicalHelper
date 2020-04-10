package com.llsparrow.healthassistant.feature_secure_storage_impl.di

import com.llsparrow.core_base_api.di.CoreBaseApi
import com.llsparrow.healthassistant.core_di.holder.FeatureApiHolder
import com.llsparrow.healthassistant.core_di.holder.FeatureContainer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SecureStorageFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer
) : FeatureApiHolder(featureContainer) {
    override fun initializeDependencies(): Any {
        val featureSecureStorageDependencies = DaggerFeatureSecureStorageDependenciesComponent.builder()
                .coreBaseApi(getFeature(CoreBaseApi::class.java))
                .build()
        return DaggerFeatureSecureStorageComponent.builder()
                .featureSecureStorageDependencies(featureSecureStorageDependencies)
                .build()
    }
}