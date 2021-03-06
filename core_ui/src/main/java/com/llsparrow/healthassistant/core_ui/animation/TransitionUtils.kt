package com.llsparrow.healthassistant.core_ui.animation

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.llsparrow.healthassistant.core_ui.R

fun Fragment.slideInFromRight(enter: Boolean): Animation? {
    return if (enter) {
        AnimationUtils.loadAnimation(activity, R.anim.slide_in_right)
    } else {
        AnimationUtils.loadAnimation(activity, R.anim.fade_out_slide_out_left)
    }
}

fun Fragment.slideUpFromBottom(enter: Boolean): Animation? {
    return if (enter) {
        AnimationUtils.loadAnimation(activity, R.anim.slide_in_from_bottom)
    } else {
        AnimationUtils.loadAnimation(activity, R.anim.slide_out_from_top)
    }
}

fun Fragment.slideUpFromBottom2(enter: Boolean): Animation? {
    return if (enter) {
        AnimationUtils.loadAnimation(activity, R.anim.slide_up_from_bottom)
    } else {
        AnimationUtils.loadAnimation(activity, R.anim.slide_out_to_bottom)
    }
}


fun Fragment.fadeIn(enter: Boolean): Animation? {
    return if (enter) {
        AnimationUtils.loadAnimation(activity, R.anim.fade_in)
    } else {
        AnimationUtils.loadAnimation(activity, R.anim.fade_out)
    }
}

fun Fragment.slideInFromLeft(enter: Boolean): Animation? {
    return if (enter) {
        AnimationUtils.loadAnimation(activity, R.anim.fade_in_slide_in_left)
    } else {
        AnimationUtils.loadAnimation(activity, R.anim.slide_out_right)
    }
}


fun Fragment.popUp(enter: Boolean): Animation? {
    return if (enter) {
        AnimationUtils.loadAnimation(activity, R.anim.popup_in)
    } else {
        AnimationUtils.loadAnimation(activity, R.anim.popup_out)
    }
}
