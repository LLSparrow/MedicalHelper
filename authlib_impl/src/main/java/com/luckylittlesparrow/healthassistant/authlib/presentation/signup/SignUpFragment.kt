package com.luckylittlesparrow.healthassistant.authlib.presentation.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.transition.TransitionInflater
import com.luckylittlesparrow.healthassistant.authlib.presentation.signin.SignInViewModel
import com.luckylittlesparrow.healthassistant.authlib_impl.databinding.FragmentCreateAccountBinding
import com.luckylittlesparrow.healthassistant.core_ui.animation.slideUpFromBottom
import com.luckylittlesparrow.healthassistant.core_ui.utils.executeAfter
import com.luckylittlesparrow.healthassistant.core_ui.utils.toast
import com.luckylittlesparrow.healthassistant.core_ui.view.AutoClearedValue
import com.luckylittlesparrow.healthassistant.core_ui.view.BaseFragment
import com.luckylittlesparrow.healthassistant.core_ui.view.viewmodel.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Gusev Andrei
 * @since  1.0
 */

class SignUpFragment : BaseFragment() {

    private val authViewModel: SignInViewModel by viewModel()

    private var binding by AutoClearedValue<FragmentCreateAccountBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        allowEnterTransitionOverlap = true
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
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

//    override fun onBackPressed(): Boolean {
//        authViewModel.onBackPressed()
//        return true
//    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return slideUpFromBottom(enter)
    }

    private fun observeViewModel() {
        authViewModel.loginResult().observe(this) {
            toast(it.id)
        }
    }
}