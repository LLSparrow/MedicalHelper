package com.llsparrow.healthassistant.feature_secure_storage_impl.di

import com.llsparrow.core_base_api.di.CoreBaseApi
import com.llsparrow.healthassistant.core_di.FeatureScope
import dagger.Component
import com.llsparrow.healthassistant.feature_secure_storage_api.di.SecureStorageFeatureApi

@Component(
        dependencies = [FeatureSecureStorageDependencies::class],
        modules = [SecureStorageModule::class]
)
@FeatureScope
interface FeatureSecureStorageComponent : SecureStorageFeatureApi

@Component(
        dependencies = [CoreBaseApi::class]
)
@FeatureScope
interface FeatureSecureStorageDependenciesComponent : FeatureSecureStorageDependencies