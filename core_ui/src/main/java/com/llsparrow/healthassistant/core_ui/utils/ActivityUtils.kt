package com.llsparrow.healthassistant.core_ui.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.llsparrow.healthassistant.core_ui.R


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

fun AppCompatActivity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun AppCompatActivity.toast(@StringRes messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, messageResId, duration).show()
}


fun <T : ViewDataBinding> Activity.setDataBindingContentView(@LayoutRes res: Int): T {
    return DataBindingUtil.setContentView(this, res)
}

fun Activity?.addFragment(
    @IdRes id: Int = R.id.container,
    fragment: Fragment,
    tag: String? = null,
    addToBackStack: Boolean = true
) {
    val compatActivity = this as? AppCompatActivity ?: return
    compatActivity.supportFragmentManager.beginTransaction()
        .apply {
            add(id, fragment, tag)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            if (addToBackStack) {
                addToBackStack(null)
            }
            commit()
        }
}

fun Activity?.replaceFragment(
    @IdRes id: Int = R.id.container,
    fragment: Fragment,
    tag: String? = null,
    addToBackStack: Boolean = false
) {
    val compatActivity = this as? AppCompatActivity ?: return
    compatActivity.supportFragmentManager.beginTransaction()
        .apply {
            replace(id, fragment, tag)
            if (addToBackStack) {
                addToBackStack(null)
            }
            commit()
        }
}
