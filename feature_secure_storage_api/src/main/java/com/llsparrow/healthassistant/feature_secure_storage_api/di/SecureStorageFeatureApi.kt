package com.llsparrow.healthassistant.feature_secure_storage_api.di

import com.llsparrow.healthassistant.feature_secure_storage_api.SecureStorage

interface SecureStorageFeatureApi {
    fun secureStorage(): SecureStorage
}