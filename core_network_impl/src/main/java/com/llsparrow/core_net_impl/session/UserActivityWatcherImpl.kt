package com.llsparrow.core_net_impl.session

import android.content.Context
import android.os.CountDownTimer
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.llsparrow.core_network_api.session.UserActivityWatcher
import com.llsparrow.healthassistant.core_di.FeatureScope
import java.lang.ref.WeakReference
import javax.inject.Inject

@FeatureScope
class UserActivityWatcherImpl @Inject constructor() : UserActivityWatcher {

    private var activityInFocus: WeakReference<AppCompatActivity>? = null

    override fun init(activity: AppCompatActivity) {
        activityInFocus = WeakReference(activity)
        timer.start()
    }

    override fun onUserInteracted() {
        timer.start()
    }

    private val timer = object : CountDownTimer(30000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
        }

        override fun onFinish() {
            val activity = activityInFocus?.get()
            if (activity != null) {
                val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                val binder = activity.currentFocus?.windowToken ?: activity.window.decorView.windowToken ?: return

                inputMethodManager.hideSoftInputFromWindow(
                    binder,
                    0
                )
            }
            activityInFocus?.clear()
        }
    }
}