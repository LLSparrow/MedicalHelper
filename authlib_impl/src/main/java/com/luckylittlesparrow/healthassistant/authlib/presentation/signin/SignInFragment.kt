package com.luckylittlesparrow.healthassistant.authlib.presentation.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.luckylittlesparrow.healthassistant.authlib_impl.BuildConfig
import com.luckylittlesparrow.healthassistant.authlib_impl.databinding.FragmentLoginBinding
import com.luckylittlesparrow.healthassistant.core_ui.animation.slideUpFromBottom
import com.luckylittlesparrow.healthassistant.core_ui.utils.executeAfter
import com.luckylittlesparrow.healthassistant.core_ui.utils.toast
import com.luckylittlesparrow.healthassistant.core_ui.view.AutoClearedValue
import com.luckylittlesparrow.healthassistant.core_ui.view.BaseFragment
import com.luckylittlesparrow.healthassistant.core_ui.view.safeActivity
import com.luckylittlesparrow.healthassistant.core_ui.view.viewmodel.observe
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Gusev Andrei
 * @since  1.0
 */

private const val GOOGLE_SIGN_IN = 1

class SignInFragment : BaseFragment() {

    private val authViewModel: SignInViewModel by viewModel()

    private var binding by AutoClearedValue<FragmentLoginBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel()
        binding.executeAfter {
            viewModel = authViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        authLoginSignUpButton.setOnClickListener {
//            authViewModel.createAccount(it,authLoginEmailEditText,)
//        }
    }

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
}