package com.llsparrow.healthassistant.feature_authentication_impl.presentation.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import com.llsparrow.healthassistant.core_di.holder.FeatureUtils
import com.llsparrow.healthassistant.core_ui.animation.slideUpFromBottom
import com.llsparrow.healthassistant.core_ui.utils.executeAfter
import com.llsparrow.healthassistant.core_ui.utils.toast
import com.llsparrow.healthassistant.core_ui.view.AutoClearedValue
import com.llsparrow.healthassistant.core_ui.view.BaseFragment
import com.llsparrow.healthassistant.core_ui.view.viewmodel.observe
import com.llsparrow.healthassistant.core_ui.view.viewmodel.withViewModel
import com.llsparrow.healthassistant.feature_authentication_api.di.AuthenticationFeatureApi
import com.llsparrow.healthassistant.feature_authentication_impl.databinding.FragmentCreateAccountBinding
import com.llsparrow.healthassistant.feature_authentication_impl.di.AuthenticationFeatureComponent
import com.llsparrow.healthassistant.feature_authentication_impl.presentation.signin.SignInViewModel
import javax.inject.Inject


class SignUpFragment : BaseFragment<FragmentCreateAccountBinding>() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        allowEnterTransitionOverlap = true
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCreateAccountBinding {
        return FragmentCreateAccountBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(binding: FragmentCreateAccountBinding, savedInstanceState: Bundle?) {
        withViewModel<SignUpViewModel>(viewModelFactory) {
            signUpViewModel = this
        }

        binding.executeAfter {
            viewModel = signUpViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

//    override fun onBackPressed(): Boolean {
//        authViewModel.onBackPressed()
//        return true
//    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return slideUpFromBottom(enter)
    }

    override fun injectDependencies(context: Context) {
        val component =
            FeatureUtils.getFeature<AuthenticationFeatureComponent>(context, AuthenticationFeatureApi::class.java)
        component.inject(this)
    }

}