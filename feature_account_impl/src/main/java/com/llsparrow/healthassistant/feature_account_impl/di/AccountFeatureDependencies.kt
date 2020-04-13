package com.llsparrow.healthassistant.feature_account_impl.di

import android.content.Context
import com.llsparrow.core_base_api.io.AppDispatchers
import com.llsparrow.healthassistant.feature_secure_storage_api.SecureStorage

interface AccountFeatureDependencies {
    fun context(): Context

    fun secureStorage(): SecureStorage

    fun appDispatchers(): AppDispatchers
}

