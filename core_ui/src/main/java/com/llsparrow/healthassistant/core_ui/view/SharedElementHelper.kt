package com.llsparrow.healthassistant.core_ui.view

/**
 * @author Gusev Andrei
 * @since  1.0
 */
import android.view.View
import androidx.collection.ArrayMap

class SharedElementHelper {
    private val _sharedElements = ArrayMap<View, String?>()

    val sharedElements: Map<View, String?>
        get() = _sharedElements

    fun addSharedElement(view: View, transitionName: String? = view.transitionName) {
        _sharedElements[view] = transitionName ?: view.transitionName
    }

    fun isEmpty(): Boolean = _sharedElements.isEmpty
}
