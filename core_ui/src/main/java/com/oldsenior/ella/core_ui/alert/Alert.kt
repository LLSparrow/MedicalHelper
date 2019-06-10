package com.oldsenior.ella.core_ui.alert

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Alert(
    val title: String? = null,
    val titleRes: Int? = null,
    val description: String? = null,
    val descriptionRes: Int? = null,
    val okAction: AlertAction? = null,
    val negativeAction: AlertAction? = null,
    val cancelAction: AlertAction? = null,
    val dismissAction: AlertAction? = null,
    val type: AlertType = AlertType.DIALOG
) : Parcelable
