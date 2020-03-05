package com.luckylittlesparrow.healthassistant.authlib.presentation.signin

import com.luckylittlesparrow.healthassistant.core_ui.view.SharedElementHelper

interface SignInWizardPart{
    fun navigateToSignUp(sharedElement: SharedElementHelper)
    fun onBackPressed()
}