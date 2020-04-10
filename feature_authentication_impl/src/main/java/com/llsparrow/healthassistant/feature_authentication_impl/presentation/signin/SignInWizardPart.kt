package com.llsparrow.healthassistant.feature_authentication_impl.presentation.signin

import com.llsparrow.healthassistant.core_ui.view.SharedElementHelper

interface SignInWizardPart{
    fun navigateToSignUp(sharedElement: SharedElementHelper)
    fun onBackPressed()
}