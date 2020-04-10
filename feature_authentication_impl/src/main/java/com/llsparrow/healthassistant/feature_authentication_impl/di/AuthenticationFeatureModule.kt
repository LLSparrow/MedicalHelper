package com.llsparrow.healthassistant.feature_authentication_impl.di

import com.google.firebase.auth.FirebaseAuth
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_authentication_api.domain.AuthInteractor
import com.llsparrow.healthassistant.feature_authentication_api.navigation.AuthLauncher
import com.llsparrow.healthassistant.feature_authentication_impl.data.AuthRepositoryImpl
import com.llsparrow.healthassistant.feature_authentication_impl.data.service.AuthService
import com.llsparrow.healthassistant.feature_authentication_impl.data.service.AuthServiceImpl
import com.llsparrow.healthassistant.feature_authentication_impl.domain.AuthInteractorImpl
import com.llsparrow.healthassistant.feature_authentication_impl.domain.AuthRepository
import com.llsparrow.healthassistant.feature_authentication_impl.navigation.AuthLauncherImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class AuthenticationFeatureModule {

    @FeatureScope
    @Binds
    abstract fun provideAuthLauncher(launcher: AuthLauncherImpl): AuthLauncher

    @FeatureScope
    @Binds
    abstract fun provideAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @FeatureScope
    @Binds
    abstract fun provideAuthService(service: AuthServiceImpl): AuthService

    @FeatureScope
    @Binds
    abstract fun provideAuthInteractor(interactor: AuthInteractorImpl): AuthInteractor

    companion object {

        @JvmStatic
        @Provides
        fun provideFirebaseAuth(): FirebaseAuth {
            return FirebaseAuth.getInstance()
        }
    }
}