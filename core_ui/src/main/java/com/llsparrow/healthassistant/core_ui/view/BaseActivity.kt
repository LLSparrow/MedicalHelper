package com.llsparrow.healthassistant.core_ui.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.llsparrow.core_network_api.data.exception.Alert

abstract class BaseActivity : AppCompatActivity, BaseActivityCallbacks {
    private val activityDelegate: BaseActivityDelegate by lazy {
        BaseActivityDelegate(this)
    }

    constructor(contentLayoutId: Int) : super(contentLayoutId)

    constructor() : super()

    override fun attachBaseContext(base: Context) {
        //   super.attachBaseContext(activityDelegate.setLocale(base))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityDelegate.onPreCreate()
        super.onCreate(savedInstanceState)
        activityDelegate.onPostCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        activityDelegate.onResume()
    }

//    override fun getMenuInflater(): MenuInflater {
//        return activityDelegate.getMenuInflater()
//    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        activityDelegate.onUserInteraction()
    }

    override fun onPause() {
        super.onPause()
        activityDelegate.onPause()
    }

    fun onError(alert: Alert) {
        activityDelegate.onError(alert)
    }

    fun showError(alert: Alert) {
        onError(alert)
    }
}