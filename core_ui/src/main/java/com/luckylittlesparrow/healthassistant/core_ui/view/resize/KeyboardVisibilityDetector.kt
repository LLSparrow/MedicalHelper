package com.luckylittlesparrow.healthassistant.core_ui.view.resize

import android.view.ViewTreeObserver

object KeyboardVisibilityDetector {

    fun listen(viewHolder: ActivityViewHolder, listener: (KeyboardVisibilityChanged) -> Unit) {
        val detector = Detector(viewHolder, listener)
        viewHolder.nonResizableLayout.viewTreeObserver.addOnPreDrawListener(detector)
        viewHolder.onDetach {
            viewHolder.nonResizableLayout.viewTreeObserver.removeOnPreDrawListener(detector)
        }
    }

    private class Detector(
        val viewHolder: ActivityViewHolder,
        val listener: (KeyboardVisibilityChanged) -> Unit
    ) : ViewTreeObserver.OnPreDrawListener {

        private var previousHeight: Int = -1
        private var screenSize: Int = -1

        override fun onPreDraw(): Boolean {
            val detected = detect()

            // The layout flickers for a moment, usually on the first
            // animation. Intercepting this pre-draw seems to solve the problem.
            return detected.not()
        }

        private fun detect(): Boolean {
            val contentHeight = viewHolder.resizableLayout.height
            if (contentHeight == previousHeight) {
                return false
            }

            if (previousHeight != -1 && screenSize != -1) {
                val isKeyboardVisible = contentHeight < screenSize

                listener(
                    KeyboardVisibilityChanged(
                        visible = isKeyboardVisible,
                        contentHeight = contentHeight,
                        contentHeightBeforeResize = previousHeight
                    )
                )
            } else {
                screenSize = contentHeight
            }

            previousHeight = contentHeight
            return true
        }
    }
}

data class KeyboardVisibilityChanged(
    val visible: Boolean,
    val contentHeight: Int,
    val contentHeightBeforeResize: Int
)
