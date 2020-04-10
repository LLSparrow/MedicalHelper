package com.llsparrow.healthassistant.feature_authentication_impl.di

import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_authentication_impl.navigation.AuthRouter
import com.llsparrow.healthassistant.feature_authentication_impl.presentation.signin.SignInWizardPart
import dagger.Module
import dagger.Provides

@Module
abstract class AuthenticationWizardModule {

    companion object {
        @JvmStatic
        @FeatureScope
        @Provides
        internal fun provideAuthLauncher(router: AuthRouter): SignInWizardPart {
            return router.signInWizardPart
        }
    }
}