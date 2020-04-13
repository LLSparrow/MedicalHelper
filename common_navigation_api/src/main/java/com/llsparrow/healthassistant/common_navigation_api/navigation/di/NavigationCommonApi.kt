package com.llsparrow.healthassistant.common_navigation_api.navigation.di

import com.llsparrow.healthassistant.common_navigation_api.navigation.auth.AuthLauncher
import com.llsparrow.healthassistant.common_navigation_api.navigation.auth.AuthRouter
import com.llsparrow.healthassistant.common_navigation_api.navigation.main.MainMenuRouter

interface NavigationCommonApi {
    fun authRouter(): AuthRouter

    fun authLauncher(): AuthLauncher

    fun mainMenuRouter(): MainMenuRouter
}