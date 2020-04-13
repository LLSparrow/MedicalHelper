package com.llsparrow.healthassistant.common_navigation_api.navigation.auth

import com.llsparrow.healthassistant.core_ui.view.SharedElementHelper

interface AuthRouter {
    fun navigateToSignUp(sharedElement: SharedElementHelper)

    fun loginFinished()

    fun registrationFinished()
}