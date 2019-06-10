package com.oldsenior.ella.core_ui.utils

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable


const val KEYBOARD_MIN_HEIGHT_RATIO = 0.15

/**
 * Manages access to the Android soft keyboard.
 */
class KeyboardManager {

    companion object {

        /**
         * Observable of the status of the keyboard. Subscribing to this creates a
         * Global Layout Listener which is automatically removed when this
         * observable is disposed.
         */
        @JvmStatic
        fun observeKeyboardEvents(activity: AppCompatActivity) = LiveDataReactiveStreams
            .fromPublisher(
                Observable.create<KeyboardStatus> { emitter ->
                    val activityRoot = (activity.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)

                    val r = Rect()
                    var lastStatus: KeyboardStatus = KeyboardStatus.CLOSED

                    val globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {


                        activityRoot.getWindowVisibleDisplayFrame(r)

                        val screenHeight = activityRoot.rootView.height
                        val heightDiff = screenHeight - r.height()

                        val newStatus =
                            if (heightDiff > screenHeight * KEYBOARD_MIN_HEIGHT_RATIO)
                                KeyboardStatus.OPEN
                            else KeyboardStatus.CLOSED

                        if (lastStatus == newStatus) return@OnGlobalLayoutListener
                        else {
                            lastStatus = newStatus
                            emitter.onNext(lastStatus)
                        }

                    }

                    activityRoot.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)

                    emitter.setCancellable {
                        activityRoot.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
                    }

                }.toFlowable(BackpressureStrategy.LATEST)
            )
    }
}

enum class KeyboardStatus {
    OPEN, CLOSED
}