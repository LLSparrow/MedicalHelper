package com.llsparrow.healthassistant.feature_authentication_impl.navigation

import android.content.Context
import com.llsparrow.core_base_api.navigation.NavigationController
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_di.holder.FeatureUtils
import com.llsparrow.healthassistant.feature_authentication_api.di.AuthenticationFeatureApi
import com.llsparrow.healthassistant.feature_authentication_api.navigation.AuthLauncher
import com.llsparrow.healthassistant.feature_authentication_impl.R
import com.llsparrow.healthassistant.feature_authentication_impl.di.AuthenticationFeatureComponent
import javax.inject.Inject

/**
 * @author Gusev Andrei
 * @since  1.0
 */
@FeatureScope
class AuthLauncherImpl @Inject constructor(
    private val navigationController: NavigationController,
    private val context: Context
) : AuthLauncher {

    override fun launchAuthenticationFlow() {
        createFeatureBranch(context)
        navigationController.switchTo(R.navigation.auth_graph)
    }

    private fun createFeatureBranch(context: Context) {
        val component =
            FeatureUtils.getFeature<AuthenticationFeatureComponent>(context, AuthenticationFeatureApi::class.java)
    }
}