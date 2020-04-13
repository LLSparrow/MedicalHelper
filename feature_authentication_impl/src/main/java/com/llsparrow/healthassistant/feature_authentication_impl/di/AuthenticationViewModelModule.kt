package com.llsparrow.healthassistant.feature_authentication_impl.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_di.ViewModelKey
import com.llsparrow.healthassistant.core_ui.view.viewmodel.BaseViewModelFactory
import com.llsparrow.healthassistant.feature_authentication_impl.presentation.signin.SignInViewModel
import com.llsparrow.healthassistant.feature_authentication_impl.presentation.signup.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthenticationViewModelModule {

    @FeatureScope
    @Binds
    abstract fun provideViewModelFactory(factory: BaseViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    @Binds
    abstract fun provideSignInViewModel(viewModel: SignInViewModel): ViewModel

    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    @Binds
    abstract fun provideSignUpViewModel(viewModel: SignUpViewModel): ViewModel

}