package com.llsparrow.healthassistant.feature_main_impl.di

import com.llsparrow.healthassistant.common_navigation_api.navigation.auth.AuthLauncher

interface MainMenuFeatureDependencies {

    fun authLauncher(): AuthLauncher
}