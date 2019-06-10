package com.oldsenior.ella.core_ui.alert

import android.content.Context

fun Alert.getDescription(context: Context) = description ?: descriptionRes?.let { context.getString(descriptionRes) }

fun Alert.getTitle(context: Context) = title ?: titleRes?.let { context.getString(titleRes) }