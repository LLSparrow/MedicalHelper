package com.llsparrow.healthassistant.feature_authentication_impl.navigation

import com.llsparrow.core_base_api.navigation.BaseRouter
import com.llsparrow.core_base_api.navigation.NavigationController
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_ui.navigation.toFragmentNavigatorExtras
import com.llsparrow.healthassistant.core_ui.view.SharedElementHelper
import com.llsparrow.healthassistant.feature_authentication_impl.R
import com.llsparrow.healthassistant.feature_authentication_impl.presentation.signin.SignInWizardPart
import javax.inject.Inject

/**
 * @author Gusev Andrei
 * @since  1.0
 */
@FeatureScope
class AuthRouter @Inject constructor(navigationController: NavigationController) : BaseRouter(navigationController) {

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