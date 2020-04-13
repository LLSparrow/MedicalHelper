package com.llsparrow.healthassistant.common_navigation.main

import com.llsparrow.healthassistant.common_navigation.R
import com.llsparrow.healthassistant.common_navigation_api.navigation.core.BaseRouter
import com.llsparrow.healthassistant.common_navigation_api.navigation.core.NavigationController
import com.llsparrow.healthassistant.common_navigation_api.navigation.main.MainMenuRouter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainMenuRouterImpl @Inject constructor(navController: NavigationController) : BaseRouter(navController),
    MainMenuRouter {
    override fun navigateToAuthorization() {
        navigate(R.id.action_mainMenuFragment_to_auth_navigation)
    }
}