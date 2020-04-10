package com.llsparrow.healthassistant.feature_main_impl

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.llsparrow.feature_main_api.di.MainMenuFeatureApi
import com.llsparrow.healthassistant.core_di.holder.FeatureUtils
import com.llsparrow.healthassistant.core_ui.view.BaseFragment
import com.llsparrow.healthassistant.core_ui.view.BottomBarCallback
import com.llsparrow.healthassistant.core_ui.view.safeActivity
import com.llsparrow.healthassistant.feature_authentication_api.di.AuthenticationFeatureApi
import com.llsparrow.healthassistant.feature_authentication_api.navigation.AuthLauncher
import com.llsparrow.healthassistant.feature_main_impl.di.MainMenuFeatureComponent
import com.llsparrow.healthassistant.main_menu.R
import com.llsparrow.healthassistant.main_menu.databinding.FragmentMainMenuBinding
import kotlinx.android.synthetic.main.fragment_main_menu.*
import javax.inject.Inject

class MainMenuFragment : BaseFragment<FragmentMainMenuBinding>(R.layout.fragment_main_menu) {

    @Inject
    lateinit var authLauncher: AuthLauncher

    override fun onResume() {
        super.onResume()
        (safeActivity as BottomBarCallback).showBottomNavigationBar()
    }

    override fun onViewCreated(binding: FragmentMainMenuBinding, savedInstanceState: Bundle?) {
        setTitle(R.string.main_menu)
        toLogin.setOnClickListener {

            authLauncher.launchAuthenticationFlow()
            (safeActivity as BottomBarCallback).hideBottomNavigationBar()
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentMainMenuBinding {
        return FragmentMainMenuBinding.inflate(inflater, container, false)
    }

    override fun injectDependencies(context: Context) {
        val component =
            FeatureUtils.getFeature<MainMenuFeatureComponent>(context, MainMenuFeatureApi::class.java)
        component.inject(this)
    }
}