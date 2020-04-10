package com.llsparrow.healthassistant.feature_authentication_api.di

import com.llsparrow.healthassistant.feature_authentication_api.navigation.AuthLauncher

interface AuthenticationFeatureApi {
    fun authLauncher(): AuthLauncher
}