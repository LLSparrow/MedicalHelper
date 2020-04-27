package com.llsparrow.core_util

import android.net.Uri
import android.util.Base64

private const val NUMBERS = "0123456789"

fun String.hasUppercase(): Boolean {
    val lowercase = this.toLowerCase()
    return lowercase != this
}

fun String.onlyDigits(): String = this.replace("\\D+".toRegex(), "")

fun String.matchEnglish(): Boolean =
    this.matches("^[a-zA-Z0-9\$@\$!%*?&#()+[\"][ ]\\[\\]|{}~\\\\=^./_',:;`\\-^-_]+\$".toRegex())

fun String.decodeBase64() = String(Base64.decode(this, Base64.DEFAULT))

fun String.isSequence() = NUMBERS.contains(this)

fun String.isSequenceReversed() = NUMBERS.reversed().contains(this)

fun String.toUri(): Uri = Uri.parse(this)