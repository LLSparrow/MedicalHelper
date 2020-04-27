package com.llsparrow.healthassistant.feature_authentication_impl.presentation.signin

import android.content.Intent
import android.view.View
import androidx.lifecycle.LiveData
import com.llsparrow.healthassistant.common_navigation_api.navigation.auth.AuthRouter
import com.llsparrow.healthassistant.core_base_impl.livedata.SingleLiveEvent
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_ui.view.SharedElementHelper
import com.llsparrow.healthassistant.core_ui.view.viewmodel.BaseViewModel
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import com.llsparrow.healthassistant.feature_authentication_api.domain.AuthInteractor
import com.llsparrow.healthassistant.feature_authentication_impl.domain.GoogleInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

@FeatureScope
class SignInViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val googleInteractor: GoogleInteractor,
    private val authRouter: AuthRouter
) : BaseViewModel() {

    val email = SingleLiveEvent<String>()
    val password = SingleLiveEvent<String>()
//
//    private val isEmailValid: LiveData<ValidatorState> = Transformations.map(email) {
//        when (UsernameValidator.isValid(it)) {
//            true -> InValid
//            false -> Valid
//        }
//    }
//
//    val emailError: LiveData<String> = Transformations.map(isEmailValid) {
//        when (it) {
//            Valid -> ""
//            InValid -> "Must contain only letters numbers and underscores" // It should be get from R.string
//        }
//    }.debounce(duration = 200L, coroutineScope = this)

    fun signInAnonymously() {
        launch {
            authInteractor.signInAnonymously()
        }
    }

    fun signIn() {
        val email = email.value
        val password = password.value
        launch {

            if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                authInteractor.signIn(email, password)
            }

        }
    }

    fun signOut() {
        launch {
            authInteractor.signOut {

            }
        }
    }

    fun signInWithGoogle(data: Intent?) {
        data?.let {
            launch {
                val user = googleInteractor(GoogleInteractor.Params(data))
                if (user != null) {
                    authRouter.loginFinished()
                } else {

                }
            }
        }
    }

    fun createAccount(createAccountButton: View) {
        val sharedElement = SharedElementHelper()
            .apply { addSharedElement(createAccountButton, createAccountButton.transitionName) }

        authRouter.navigateToSignUp(sharedElement)
    }

    fun onBackPressed() {
        //  signInWizardPart.onBackPressed()
    }
}