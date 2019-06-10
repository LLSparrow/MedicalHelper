package com.oldsenior.ella.core_ui.error

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.oldsenior.ella.core_ui.R
import com.oldsenior.ella.core_ui.alert.Alert
import com.oldsenior.ella.core_ui.alert.getDescription
import com.oldsenior.ella.core_ui.alert.getTitle


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
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val ctx = requireContext()
        checkNotNull(arguments)

        val alert = arguments!!.getParcelable<Alert>(ALERT)
            ?: throw IllegalArgumentException("Argument with key $ALERT not found")

        val builder = AlertDialog.Builder(ctx)
            .setTitle(alert.getTitle(ctx) ?: getString(R.string.error))
            .setMessage(alert.getDescription(ctx))
            .setPositiveButton(R.string.ok) { _, _ -> alert.okAction?.onAction(this) }
            .setOnCancelListener { alert.cancelAction?.onAction(this) }

        alert.negativeAction?.let {
            builder.setNegativeButton(R.string.cancel) { _, _ ->
                it.onAction(
                    this
                )
            }
        }
        return builder.create()
    }

}