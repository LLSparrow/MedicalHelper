package com.luckylittlesparrow.core_net_api.data.exception

import android.content.Context
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
    val type: AlertType = AlertType.DIALOG,
    val cancelable: Boolean = false
) : Parcelable

enum class AlertType {
    TOAST,
    DIALOG
}

fun Alert.getDescription(context: Context) = description ?: descriptionRes?.let { context.getString(descriptionRes) }

fun Alert.getTitle(context: Context) = title ?: titleRes?.let { context.getString(titleRes) }