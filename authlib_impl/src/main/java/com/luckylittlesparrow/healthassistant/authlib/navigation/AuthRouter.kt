package com.luckylittlesparrow.healthassistant.authlib.navigation

import com.luckylittlesparrow.core_base_api.navigation.BaseRouter
import com.luckylittlesparrow.core_base_api.navigation.NavigationController
import com.luckylittlesparrow.healthassistant.authlib.presentation.signin.SignInWizardPart
import com.luckylittlesparrow.healthassistant.authlib_impl.R
import com.luckylittlesparrow.healthassistant.core_ui.navigation.toFragmentNavigatorExtras
import com.luckylittlesparrow.healthassistant.core_ui.view.SharedElementHelper

/**
 * @author Gusev Andrei
 * @since  1.0
 */
class AuthRouter(navigationController: NavigationController) : BaseRouter(navigationController) {

    val signInWizardPart = object : SignInWizardPart {
        override fun onBackPressed() {
            onNavigationBackPressed()
        }

        override fun navigateToSignUp(sharedElement: SharedElementHelper) {
            navigate(
                R.id.action_signInFragment_to_signUpFragment,
                extras = sharedElement.toFragmentNavigatorExtras()
            )
        }
    }
}