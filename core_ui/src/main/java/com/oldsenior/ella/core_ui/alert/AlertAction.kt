package com.oldsenior.ella.core_ui.alert

import android.os.Parcelable
import androidx.fragment.app.DialogFragment

interface AlertAction : Parcelable {
    fun onAction(dialogFragment: DialogFragment)
}