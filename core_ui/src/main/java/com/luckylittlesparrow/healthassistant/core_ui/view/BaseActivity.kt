package com.luckylittlesparrow.healthassistant.core_ui.view

import androidx.appcompat.app.AppCompatActivity
import com.luckylittlesparrow.core_net_api.data.exception.Alert
import com.luckylittlesparrow.healthassistant.core_ui.error.DialogHelper

abstract class BaseActivity : AppCompatActivity {
    private val dialogHelper = DialogHelper()

    constructor(contentLayoutId: Int) : super(contentLayoutId)

    constructor() : super()

    open fun onError(alert: Alert) {
        dialogHelper.showError(this, alert)
    }
}