package com.llsparrow.healthassistant.feature_authentication_impl.presentation.signin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.llsparrow.healthassistant.core_di.holder.FeatureUtils
import com.llsparrow.healthassistant.core_ui.animation.slideUpFromBottom
import com.llsparrow.healthassistant.core_ui.utils.executeAfter
import com.llsparrow.healthassistant.core_ui.utils.toast
import com.llsparrow.healthassistant.core_ui.view.AutoClearedValue
import com.llsparrow.healthassistant.core_ui.view.BaseFragment
import com.llsparrow.healthassistant.core_ui.view.safeActivity
import com.llsparrow.healthassistant.core_ui.view.viewmodel.observe
import com.llsparrow.healthassistant.core_ui.view.viewmodel.withViewModel
import com.llsparrow.healthassistant.feature_authentication_api.di.AuthenticationFeatureApi
import com.llsparrow.healthassistant.feature_authentication_impl.BuildConfig
import com.llsparrow.healthassistant.feature_authentication_impl.databinding.FragmentLoginBinding
import com.llsparrow.healthassistant.feature_authentication_impl.di.AuthenticationFeatureComponent
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

/**
 * @author Gusev Andrei
 * @since  1.0
 */

private const val GOOGLE_SIGN_IN = 1

class SignInFragment : BaseFragment<FragmentLoginBinding>() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var authViewModel: SignInViewModel

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(binding: FragmentLoginBinding, savedInstanceState: Bundle?) {

        withViewModel<SignInViewModel>(viewModelFactory) {
            authViewModel = this
            observeViewModel()
        }

        binding.executeAfter {
            viewModel = authViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        authLoginSignUpButton.setOnClickListener {
          //  authViewModel.createAccount(it, authLoginEmailEditText,)
        }
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        withViewModel<SignInViewModel>(viewModelFactory) {
//            authViewModel = this
//            observeViewModel()
//        }
//    }

    override fun onBackPressed(): Boolean {
        authViewModel.onBackPressed()
        return true
    }

    private fun observeViewModel() {
        authViewModel.loginResult().observe(this) {
            toast(it.id)
        }
    }

    private fun signInWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
            .requestEmail()
            .build()

        val signInIntent = GoogleSignIn.getClient(safeActivity, gso).signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GOOGLE_SIGN_IN) {
                authViewModel.signInWithGoogle(data)
            }
        }
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return slideUpFromBottom(enter)
    }

    override fun injectDependencies(context: Context) {
        val component =
            FeatureUtils.getFeature<AuthenticationFeatureComponent>(context, AuthenticationFeatureApi::class.java)
        component.inject(this)
    }
}