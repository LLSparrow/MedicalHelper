@file:Suppress("UNCHECKED_CAST")

package com.luckylittlesparrow.healthassistant.core_ui.utils

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.luckylittlesparrow.core_net_api.data.exception.Alert
import com.luckylittlesparrow.core_util.toUri
import com.luckylittlesparrow.healthassistant.core_ui.R
import com.luckylittlesparrow.healthassistant.core_ui.action.RequestPermissionAction
import com.luckylittlesparrow.healthassistant.core_ui.error.AlertDialogFragment


inline fun <reified T : Fragment> newFragmentInstance(bundle: Bundle): T =
    T::class.java.newInstance().apply {
        arguments = bundle
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

fun Fragment.showActionBar() {
    if (activity is AppCompatActivity) (activity as AppCompatActivity).supportActionBar?.show()
}

fun Fragment.hideActionBar() {
    if (activity is AppCompatActivity) (activity as AppCompatActivity).supportActionBar?.hide()
}

fun Fragment.totalRamMemorySize(): Long {
    val mi = ActivityManager.MemoryInfo()
    val activityManager = context?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
    activityManager!!.getMemoryInfo(mi)
    return mi.totalMem / 1048576L
}

inline fun <T : ViewDataBinding> T.executeAfter(block: T.() -> Unit) {
    block()
    executePendingBindings()
}

inline val Fragment.safeActivity: FragmentActivity
    get() = activity ?: throw IllegalStateException("Fragment not attached")

fun <T> Fragment.argument(name: String): T {
    return arguments?.get(name) as? T ?: throw IllegalStateException("Argument $name not found.")
}

fun <T> Fragment.argumentOr(name: String, default: T): T {
    return arguments?.get(name) as? T ?: default
}

fun Fragment.argumentOrEmpty(name: String): String {
    return arguments?.get(name) as? String ?: ""
}

fun Fragment.openMailApp(mail: String?) {
    if (mail.isNullOrEmpty()) return
    val mailto = "mailto:$mail"

    val emailIntent = Intent(Intent.ACTION_SENDTO)
    emailIntent.data = Uri.parse(mailto)

    if (emailIntent.resolveActivity(safeActivity.packageManager) != null) {
        safeActivity.startActivity(
            Intent.createChooser(
                emailIntent,
                getString(R.string.choose_mail)
            )
        )
    }
}

fun Fragment.openWeb(link: String?) {
    if (link.isNullOrEmpty()) return
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = link.toUri()
    }
    startActivity(intent)
}

fun Fragment.openPhoneCall(phone: String?) {
    if (phone.isNullOrEmpty()) return
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = "tel:$phone".toUri()
    }
    startActivity(intent)
}
