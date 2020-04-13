package com.llsparrow.healthassistant.common_navigation.auth

import com.llsparrow.healthassistant.common_navigation.R
import com.llsparrow.healthassistant.common_navigation_api.navigation.auth.AuthLauncher
import com.llsparrow.healthassistant.common_navigation_api.navigation.core.NavigationController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthLauncherImpl @Inject constructor(
    private val navigationController: NavigationController
) : AuthLauncher {

    override fun launchAuthenticationFlow() {
        navigationController.getNavController().navigate(R.id.action_mainMenuFragment_to_auth_navigation)
    }
}