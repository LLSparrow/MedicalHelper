package com.llsparrow.healthassistant.feature_authentication_impl.di

import android.content.Context
import com.llsparrow.core_base_api.io.AppDispatchers
import com.llsparrow.core_base_api.navigation.NavigationController

interface AuthenticationFeatureDependencies {
    fun context(): Context

    fun appDispatchers(): AppDispatchers

    fun navigationController(): NavigationController
}