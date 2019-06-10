package com.oldsenior.ella.core_ui.alert

import androidx.fragment.app.DialogFragment
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseAlertAction(private val actionId: Int) : AlertAction {
    override fun onAction(dialogFragment: DialogFragment) {
        val listener = when {
            dialogFragment.parentFragment is HostAlertActionListener -> dialogFragment.parentFragment as HostAlertActionListener
            dialogFragment.activity is HostAlertActionListener -> dialogFragment.activity as HostAlertActionListener
            else -> null
        }
        listener?.onAlertClicked(dialogFragment, actionId)
    }
}

interface HostAlertActionListener {
    fun onAlertClicked(dialogFragment: DialogFragment, actionId: Int)
}

@Parcelize
class OkAction : BaseAlertAction(ACTION_ID) {
    companion object {
        const val ACTION_ID = -1
    }
}

@Parcelize
class NegativeAction : BaseAlertAction(ACTION_ID) {
    companion object {
        const val ACTION_ID = -2
    }
}