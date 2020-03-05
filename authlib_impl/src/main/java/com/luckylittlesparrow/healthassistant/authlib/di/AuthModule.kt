package com.luckylittlesparrow.healthassistant.authlib.di

import com.google.firebase.auth.FirebaseAuth
import com.luckylittlesparrow.healthassistant.authlib.data.AuthRepositoryImpl
import com.luckylittlesparrow.healthassistant.authlib.data.mapper.FirebaseUserToAppUserMapper
import com.luckylittlesparrow.healthassistant.authlib.data.service.AuthService
import com.luckylittlesparrow.healthassistant.authlib.data.service.AuthServiceImpl
import com.luckylittlesparrow.healthassistant.authlib.domain.AuthInteractor
import com.luckylittlesparrow.healthassistant.authlib.domain.AuthInteractorImpl
import com.luckylittlesparrow.healthassistant.authlib.domain.AuthRepository
import com.luckylittlesparrow.healthassistant.authlib.navigation.AuthLauncher
import com.luckylittlesparrow.healthassistant.authlib.navigation.AuthLauncherImpl
import com.luckylittlesparrow.healthassistant.authlib.navigation.AuthRouter
import com.luckylittlesparrow.healthassistant.authlib.presentation.signin.SignInViewModel
import com.luckylittlesparrow.healthassistant.authlib.presentation.signin.SignInWizardPart
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Gusev Andrei
 * @since  1.0
 */
const val AUTH_SCOPE = "AUTH_SCOPE"

val authModule = module {

    single<AuthLauncher> { AuthLauncherImpl(get()) }
    single { AuthRouter(get()) }
    single { get<AuthRouter>().signInWizardPart }
    /* DATA */

    factory { FirebaseUserToAppUserMapper() }

    factory<AuthService> {
        AuthServiceImpl(
            firebaseAuth = get()
        )
    }

    factory { FirebaseAuth.getInstance() }

    factory<AuthRepository> {
        AuthRepositoryImpl(
            authService = get(),
            dispatchers = get(),
            mapper = get()
        )
    }

    /* DATA */

    /* DOMAIN */

    factory<AuthInteractor> { AuthInteractorImpl(authRepository = get()) }

    /* DOMAIN */

    /* PRESENTATION */

    viewModel<SignInViewModel>()

    /* PRESENTATION */
}