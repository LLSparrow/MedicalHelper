package com.llsparrow.healthassistant.feature_authentication_impl.presentation.signin

import android.content.Intent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.llsparrow.core_util.tools.debounce
import com.llsparrow.healthassistant.core_base_impl.livedata.SingleLiveEvent
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_ui.view.SharedElementHelper
import com.llsparrow.healthassistant.core_ui.view.viewmodel.BaseViewModel
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import com.llsparrow.healthassistant.feature_authentication_api.domain.AuthInteractor
import com.llsparrow.healthassistant.feature_authentication_impl.domain.GoogleInteractor
import com.llsparrow.healthassistant.feature_personal_info_api.validator.InValid
import com.llsparrow.healthassistant.feature_personal_info_api.validator.UsernameValidator
import com.llsparrow.healthassistant.feature_personal_info_api.validator.Valid
import com.llsparrow.healthassistant.feature_personal_info_api.validator.ValidatorState
import kotlinx.coroutines.launch
import javax.inject.Inject

@FeatureScope
class SignInViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val googleInteractor: GoogleInteractor,
    private val signInWizardPart: SignInWizardPart
) : BaseViewModel() {

    private val _loginResult = SingleLiveEvent<User>()
    fun loginResult(): LiveData<User> = _loginResult

    val email = MutableLiveData<String>()

    private val isEmailValid: LiveData<ValidatorState> = Transformations.map(email) {
        when (UsernameValidator.isValid(it)) {
            true -> InValid
            false -> Valid
        }
    }

    val emailError: LiveData<String> = Transformations.map(isEmailValid) {
        when (it) {
            Valid -> ""
            InValid -> "Must contain only letters numbers and underscores" // It should be get from R.string
        }
    }.debounce(duration = 200L, coroutineScope = this)

    fun signInAnonymously() {
        showError(coroutineContext, Throwable("Sa"))
        launch {
            authInteractor.signInAnonymously()
        }
    }

    fun signIn(username: String, password: String) {
        launch {
            authInteractor.signIn(username, password)

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
                googleInteractor(GoogleInteractor.Params(data))
            }
        }
    }

    fun createAccount(createAccountButton: View) {
        val sharedElement = SharedElementHelper()
            .apply { addSharedElement(createAccountButton, createAccountButton.transitionName) }

        signInWizardPart.navigateToSignUp(sharedElement)
    }

    fun onBackPressed() {
        signInWizardPart.onBackPressed()
    }
}