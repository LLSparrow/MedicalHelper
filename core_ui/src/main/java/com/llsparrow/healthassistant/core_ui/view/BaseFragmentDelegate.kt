package com.llsparrow.healthassistant.core_ui.view

import android.content.Context
import android.view.animation.Animation
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.llsparrow.core_network_api.data.exception.Alert
import com.llsparrow.healthassistant.core_ui.animation.popUp
import com.llsparrow.healthassistant.core_ui.error.DialogHelper

interface BaseFragmentCallbacks {
    fun injectDependencies(context: Context) = Unit

    fun onBackPressed(): Boolean = false
}

class BaseFragmentDelegate(private val fragment: Fragment) {

    private val dialogHelper = DialogHelper()

    private val baseCallbacks = fragment as BaseFragmentCallbacks

    fun onAttach(context: Context) {
        baseCallbacks.injectDependencies(context)
    }

    fun onError(alert: Alert) {
        dialogHelper.showError(fragment.activity as BaseActivity, alert)
    }

    fun setTitle(@StringRes titleResId: Int) {
        val activity = fragment.activity as? AppCompatActivity
        activity?.supportActionBar?.setTitle(titleResId)
    }

    fun setTitle(title: String) {
        val activity = fragment.activity as? AppCompatActivity
        activity?.supportActionBar?.title = title
    }

    fun showHomeUpEnabled(enabled: Boolean) {
        val activity = fragment.activity as AppCompatActivity?
        activity?.supportActionBar?.run {
            setDisplayHomeAsUpEnabled(enabled)
            setDisplayShowHomeEnabled(enabled)
        }
    }

    fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return fragment.popUp(enter)
    }
}