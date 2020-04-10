package com.llsparrow.healthassistant.core_ui.error

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.llsparrow.core_network_api.data.exception.Alert
import com.llsparrow.core_network_api.data.exception.getDescription
import com.llsparrow.core_network_api.data.exception.getTitle
import com.llsparrow.healthassistant.core_ui.R


class AlertDialogFragment : DialogFragment() {

    companion object {
        private const val ALERT = "alert"

        fun newInstance(alert: Alert): AlertDialogFragment {
            val errorDialogFragment = AlertDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable(ALERT, alert)
            errorDialogFragment.arguments = bundle
            return errorDialogFragment
        }

        fun show(alert: Alert, manager: FragmentManager, tag: String) =
            newInstance(alert).show(manager, tag)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val ctx = context!!

        val alert = arguments?.getParcelable<Alert>(ALERT)
            ?: throw IllegalArgumentException("Argument with key $ALERT not found")

        isCancelable = alert.cancelable
        val builder = AlertDialog.Builder(ctx)
            .setTitle(alert.getTitle(ctx) ?: getString(R.string.error))
            .setCancelable(alert.cancelable)
            .setMessage(alert.getDescription(ctx))
            .setPositiveButton(alert.okAction?.descriptionRes ?: R.string.ok) { _, _ ->
                alert.okAction?.onAction(this)
                dismiss()
            }
            .setOnCancelListener { alert.cancelAction?.onAction(this) }

        alert.negativeAction?.let {
            builder.setNegativeButton(alert.negativeAction?.descriptionRes ?: R.string.cancel) { _, _ ->
                it.onAction(
                    this
                )
                dismiss()
            }
        }
        return builder.create()
    }
}