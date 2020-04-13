package com.llsparrow.healthassistant.feature_authentication_impl.presentation.signup

import androidx.lifecycle.LiveData
import com.llsparrow.healthassistant.common_navigation_api.navigation.auth.AuthRouter
import com.llsparrow.healthassistant.core_base_impl.livedata.SingleLiveEvent
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_ui.view.viewmodel.BaseViewModel
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import com.llsparrow.healthassistant.feature_authentication_api.domain.AuthInteractor
import javax.inject.Inject

@FeatureScope
class SignUpViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val authRouter: AuthRouter
) : BaseViewModel() {

    private val _loginResult = SingleLiveEvent<User>()
    fun loginResult(): LiveData<User> = _loginResult

    val email = SingleLiveEvent<String>()
    val password = SingleLiveEvent<String>()

    fun createAccount() {
        authRouter.registrationFinished()
    }
}