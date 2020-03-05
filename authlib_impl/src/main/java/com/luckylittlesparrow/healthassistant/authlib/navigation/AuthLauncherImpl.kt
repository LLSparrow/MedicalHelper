package com.luckylittlesparrow.healthassistant.authlib.navigation

import com.luckylittlesparrow.core_base_api.navigation.NavigationController
import com.luckylittlesparrow.healthassistant.authlib_impl.R

/**
 * @author Gusev Andrei
 * @since  1.0
 */
class AuthLauncherImpl constructor(
    private val navigationController: NavigationController
) : AuthLauncher {

    override fun launchAuthentificationFlow() {
        navigationController.switchTo(R.navigation.auth_graph)
    }
}