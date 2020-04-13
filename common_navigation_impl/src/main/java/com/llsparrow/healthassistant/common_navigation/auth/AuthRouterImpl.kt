package com.llsparrow.healthassistant.common_navigation.auth

import com.llsparrow.healthassistant.common_navigation.R
import com.llsparrow.healthassistant.common_navigation_api.navigation.auth.AuthRouter
import com.llsparrow.healthassistant.common_navigation_api.navigation.core.BaseRouter
import com.llsparrow.healthassistant.common_navigation_api.navigation.core.NavigationController
import com.llsparrow.healthassistant.core_ui.navigation.toFragmentNavigatorExtras
import com.llsparrow.healthassistant.core_ui.view.SharedElementHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRouterImpl @Inject constructor(navigationController: NavigationController) : BaseRouter(navigationController),
    AuthRouter {

    override fun navigateToSignUp(sharedElement: SharedElementHelper) {
        navigate(
            R.id.action_signInFragment_to_signUpFragment,
            extras = sharedElement.toFragmentNavigatorExtras()
        )
    }

    override fun loginFinished() {
        navigateUp()
    }

    override fun registrationFinished() {
        navigate(R.id.action_back_to_main_screen)
    }
}