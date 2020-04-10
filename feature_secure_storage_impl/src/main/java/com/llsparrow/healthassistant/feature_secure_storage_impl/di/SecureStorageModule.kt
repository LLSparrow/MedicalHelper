package com.llsparrow.healthassistant.feature_secure_storage_impl.di

import com.llsparrow.healthassistant.core_di.FeatureScope
import dagger.Binds
import dagger.Module
import com.llsparrow.healthassistant.feature_secure_storage_api.SecureStorage
import com.llsparrow.healthassistant.feature_secure_storage_impl.SecureStorageImpl
import com.llsparrow.healthassistant.feature_secure_storage_impl.cipher.SecureCipher
import com.llsparrow.healthassistant.feature_secure_storage_impl.cipher.SecureCipherImpl
import com.llsparrow.healthassistant.feature_secure_storage_impl.storage.PreferencesStorage
import com.llsparrow.healthassistant.feature_secure_storage_impl.storage.Storage

@Module
interface SecureStorageModule {
    @Binds
    @FeatureScope
    fun provideSecureStorage(secureStorage: SecureStorageImpl): SecureStorage

    @Binds
    @FeatureScope
    fun provideSecureCipher(secureCipher: SecureCipherImpl): SecureCipher

    @Binds
    @FeatureScope
    fun provideStorage(storage: PreferencesStorage): Storage
}