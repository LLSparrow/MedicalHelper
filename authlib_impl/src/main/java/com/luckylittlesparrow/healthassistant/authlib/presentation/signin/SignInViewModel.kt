package com.luckylittlesparrow.healthassistant.authlib.presentation.signin

import android.content.Intent
import android.view.View
import androidx.lifecycle.LiveData
import com.luckylittlesparrow.healthassistant.authlib.domain.AuthInteractor
import com.luckylittlesparrow.healthassistant.authlib.navigation.AuthRouter
import com.luckylittlesparrow.healthassistant.core_base_impl.livedata.SingleLiveEvent
import com.luckylittlesparrow.healthassistant.core_ui.view.SharedElementHelper
import com.luckylittlesparrow.healthassistant.core_ui.view.viewmodel.BaseViewModel
import com.luckylittlesparrow.healthassistant.feature_account_api.model.User
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * @author Gusev Andrei
 * @since  1.0
 */
class SignInViewModel(
    private val authInteractor: AuthInteractor,
    private val signInWizardPart: SignInWizardPart
) : BaseViewModel() {

    private val _loginResult = SingleLiveEvent<User>()
    fun loginResult(): LiveData<User> = _loginResult

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
        launch {
            authInteractor.signInWithGoogle(data)
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