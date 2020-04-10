package com.llsparrow.healthassistant.core_ui.view.resize

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.transition.ChangeBounds
import androidx.transition.ChangeScroll
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet

object FluidContentResizer {

    fun listen(activity: Activity) {
        val viewHolder = ActivityViewHolder.createFrom(activity)

        KeyboardVisibilityDetector.listen(viewHolder) {
            animateHeight(viewHolder, it)
        }
        viewHolder.onDetach {
            viewHolder.contentView.clearAnimation()
        }

        viewHolder.contentView.apply {
            post {
                setHeight(viewHolder.resizableLayout.height)
            }
        }
    }

    private fun animateHeight(viewHolder: ActivityViewHolder, event: KeyboardVisibilityChanged) {
        val contentView = viewHolder.contentView
        contentView.setHeight(event.contentHeightBeforeResize)

        val sceneRoot = contentView.parent as ViewGroup
        val transition = TransitionSet()
        transition.addTransition(ChangeBounds())
        transition.addTransition(ChangeScroll())
        transition.duration = 300
        transition.interpolator = FastOutSlowInInterpolator()
        TransitionManager.beginDelayedTransition(sceneRoot, transition)

        contentView.setHeight(event.contentHeight)
    }

    private fun View.setHeight(height: Int) {
        val params = layoutParams
        params.height = height
        layoutParams = params
    }
}