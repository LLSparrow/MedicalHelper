package com.llsparrow.core_network_api.data.exception

import android.os.Parcelable
import androidx.fragment.app.DialogFragment

abstract class AlertAction(val descriptionRes: Int? = null) : Parcelable {

    abstract fun onAction(dialogFragment: DialogFragment)
}