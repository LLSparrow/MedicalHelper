package com.luckylittlesparrow.healthassistant.feature_main_impl

import android.os.Bundle
import android.view.View
import com.luckylittlesparrow.healthassistant.authlib.navigation.AuthLauncher
import com.luckylittlesparrow.healthassistant.core_ui.view.BaseFragment
import com.luckylittlesparrow.healthassistant.core_ui.view.BottomBarCallback
import com.luckylittlesparrow.healthassistant.core_ui.view.safeActivity
import com.luckylittlesparrow.healthassistant.main_menu.R
import kotlinx.android.synthetic.main.fragment_main_menu.*
import org.koin.android.ext.android.inject

class MainMenuFragment : BaseFragment(R.layout.fragment_main_menu) {

    private val authRouter: AuthLauncher by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.main_menu)
        toLogin.setOnClickListener {

            authRouter.launchAuthentificationFlow()
            (safeActivity as BottomBarCallback).hideBottomNavigationBar()
        }
    }

    override fun onResume() {
        super.onResume()
        (safeActivity as BottomBarCallback).showBottomNavigationBar()
    }
}