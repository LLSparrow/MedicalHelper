@file:Suppress("UNCHECKED_CAST")

package com.luckylittlesparrow.healthassistant.core_ui.view

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.luckylittlesparrow.healthassistant.core_ui.view.resize.ActivityViewHolder
import com.luckylittlesparrow.healthassistant.core_ui.view.resize.KeyboardVisibilityDetector

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

fun Fragment.hideButtonOnResizeIfNoSpace(button: View) {
    val viewHolder = ActivityViewHolder.createFrom(safeActivity)

    KeyboardVisibilityDetector.listen(viewHolder) { event ->
        if (event.contentHeight <= event.contentHeightBeforeResize) {
            button.alpha = 0f
        } else {
            button.animate()
                .alpha(1f)
                .setStartDelay(150)
                .start()
        }
    }
}