package com.luckylittlesparrow.healthassistant.core_ui.view

import android.view.animation.Animation
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.luckylittlesparrow.core_net_api.data.exception.Alert
import com.luckylittlesparrow.healthassistant.core_ui.animation.popUp
import com.luckylittlesparrow.healthassistant.core_ui.error.DialogHelper
import com.orhanobut.logger.Logger


open class BaseFragment : Fragment {

    private val dialogHelper = DialogHelper()

    constructor(contentLayoutId: Int) : super(contentLayoutId)

    constructor() : super() {
        Logger.d("Init $this")
    }

    protected fun setTitle(@StringRes titleResId: Int) {
        val activity = activity as AppCompatActivity?
        if (activity != null) {
            val actionBar = activity.supportActionBar
            actionBar?.setTitle(titleResId)
        }
    }

    protected fun setTitle(title: String) {
        val activity = activity as AppCompatActivity?
        if (activity != null) {
            val actionBar = activity.supportActionBar
            actionBar?.title = title
        }
    }

    open fun onBackPressed(): Boolean = false

    open fun onError(alert: Alert) {
        dialogHelper.showError(safeActivity, alert)
    }

    protected fun showHomeUpEnabled(enabled: Boolean) {
        val activity = activity as AppCompatActivity?
        if (activity != null) {
            activity.supportActionBar?.let {
                it.setDisplayHomeAsUpEnabled(enabled)
                it.setDisplayShowHomeEnabled(enabled)
            }
        }
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return popUp(enter)
    }
}