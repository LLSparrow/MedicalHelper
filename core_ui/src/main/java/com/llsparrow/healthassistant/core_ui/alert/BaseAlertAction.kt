package com.llsparrow.healthassistant.core_ui.alert

import androidx.fragment.app.DialogFragment
import com.llsparrow.core_network_api.data.exception.AlertAction
import kotlinx.android.parcel.Parcelize


@Parcelize
open class HostAlertAction(private val actionId: Int, private val messageRes: Int? = null) : AlertAction(messageRes) {
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
class OkAction(private val positiveMessageRes: Int? = null) : HostAlertAction(ACTION_ID, positiveMessageRes) {
    companion object {
        const val ACTION_ID = -1
    }
}

@Parcelize
class NegativeAction(private val negativeMessageRes: Int? = null) : HostAlertAction(ACTION_ID, negativeMessageRes) {
    companion object {
        const val ACTION_ID = -2
    }
}