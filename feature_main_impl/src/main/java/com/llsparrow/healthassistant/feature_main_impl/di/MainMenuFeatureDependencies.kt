package com.llsparrow.healthassistant.feature_main_impl.di

import com.llsparrow.healthassistant.feature_authentication_api.navigation.AuthLauncher

interface MainMenuFeatureDependencies {

    fun authLauncher(): AuthLauncher
}