package com.oldsenior.ella.core_ui.utils

import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.oldsenior.ella.core_ui.action.RequestPermissionAction
import com.oldsenior.ella.core_ui.alert.Alert
import com.oldsenior.ella.core_ui.error.AlertDialogFragment

inline fun <reified T : Fragment> newFragmentInstance(vararg params: Pair<String, Any>): T =
    T::class.java.newInstance().apply {
        arguments = bundleOf(*params)
    }

fun Fragment.setMultipleOnClickListeners(vararg views: View, body: () -> Unit) {
    views.forEach {
        it.setOnClickListener {
            body.invoke()
        }
    }
}

fun Fragment.openSoftKeyboard(view: View) {
    val inputMethodManager =
        activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    inputMethodManager?.showSoftInput(
        view,
        InputMethodManager.SHOW_IMPLICIT
    )
}


fun Fragment.hideSoftKeyboard(activity: FragmentActivity?) {
    if (activity != null) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val binder = activity.currentFocus?.windowToken ?: activity.window.decorView.windowToken
        ?: return

        inputMethodManager.hideSoftInputFromWindow(
            binder,
            0
        )
    }
}

fun Fragment.subscribeToKeyboardEvents(body: (KeyboardStatus) -> Unit) {
    KeyboardManager.observeKeyboardEvents(activity as AppCompatActivity)
        .observe(this, Observer(body))
}

fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, message, duration).show()
}

fun Fragment.toast(@StringRes messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, messageResId, duration).show()
}

fun Fragment.requestPermission(
    permission: String,
    permissionRequestKey: Int,
    explanationMessage: String,
    alertTitle: String
): Boolean {
    var permissionRequested = false

    if (activity != null) {
        if (ContextCompat.checkSelfPermission(activity!!, permission)
            != PackageManager.PERMISSION_GRANTED
        ) {

            val fragment = AlertDialogFragment.newInstance(
                Alert(
                    description = explanationMessage,
                    title = alertTitle,
                    okAction = RequestPermissionAction(permission, permissionRequestKey)
                )
            )
            fragment.isCancelable = false
            fragment.show(childFragmentManager, explanationMessage)

            permissionRequested = true
        }
    }
    return permissionRequested
}