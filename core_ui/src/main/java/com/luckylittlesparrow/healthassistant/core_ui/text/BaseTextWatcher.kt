package com.luckylittlesparrow.healthassistant.core_ui.text

import android.text.Editable
import android.text.TextWatcher

open class BaseTextWatcher : TextWatcher {
    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

    override fun afterTextChanged(editable: Editable) {}
}