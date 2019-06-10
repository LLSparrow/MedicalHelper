package com.oldsenior.ella.core_ui.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer


fun AppCompatActivity.openSoftKeyboard(view: View) {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(
        view,
        InputMethodManager.SHOW_IMPLICIT
    )
}


fun AppCompatActivity.hideSoftKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val binder = currentFocus?.windowToken ?: window.decorView.windowToken ?: return

    inputMethodManager.hideSoftInputFromWindow(
        binder,
        0
    )
}

fun AppCompatActivity.subscribeToKeyboardEvents(body: (KeyboardStatus) -> Unit) {
    KeyboardManager.observeKeyboardEvents(this).observe(this, Observer(body))
}

fun AppCompatActivity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun AppCompatActivity.toast(@StringRes messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, messageResId, duration).show()
}
