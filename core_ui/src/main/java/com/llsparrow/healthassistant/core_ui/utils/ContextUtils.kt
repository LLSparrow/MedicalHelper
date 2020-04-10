package com.llsparrow.healthassistant.core_ui.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.orhanobut.logger.Logger
import java.io.File
import java.io.Serializable

fun bundleOf(vararg params: Pair<String, Any>): Bundle {
    val b = Bundle()
    for (p in params) {
        val (k, v) = p
        when (v) {
            is Boolean -> b.putBoolean(k, v)
            is Byte -> b.putByte(k, v)
            is Char -> b.putChar(k, v)
            is Short -> b.putShort(k, v)
            is Int -> b.putInt(k, v)
            is Long -> b.putLong(k, v)
            is Float -> b.putFloat(k, v)
            is Double -> b.putDouble(k, v)
            is String -> b.putString(k, v)
            is CharSequence -> b.putCharSequence(k, v)
            is Parcelable -> b.putParcelable(k, v)
            is Serializable -> b.putSerializable(k, v)
            is BooleanArray -> b.putBooleanArray(k, v)
            is ByteArray -> b.putByteArray(k, v)
            is CharArray -> b.putCharArray(k, v)
            is DoubleArray -> b.putDoubleArray(k, v)
            is FloatArray -> b.putFloatArray(k, v)
            is IntArray -> b.putIntArray(k, v)
            is LongArray -> b.putLongArray(k, v)
            is Array<*> -> {
                @Suppress("UNCHECKED_CAST")
                when {
                    v.isArrayOf<Parcelable>() -> b.putParcelableArray(k, v as Array<out Parcelable>)
                    v.isArrayOf<CharSequence>() -> b.putCharSequenceArray(k, v as Array<out CharSequence>)
                    v.isArrayOf<String>() -> b.putStringArray(k, v as Array<out String>)
                    else -> throw Exception("Unsupported bundle component (${v.javaClass})")
                }
            }
            is ShortArray -> b.putShortArray(k, v)
            is Bundle -> b.putBundle(k, v)
            else -> throw Exception("Unsupported bundle component (${v.javaClass})")
        }
    }
    return b
}

fun Context.restartApp() {
    val intent = packageManager.getLaunchIntentForPackage(packageName)
    intent?.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
    System.exit(0)
}


fun Context.clearApplicationData() {
    runCatching {
        clearData(this)
    }.onFailure { throwable ->
        Logger.e(throwable, throwable.localizedMessage)
    }
}

private fun clearData(context: Context) {
    val applicationDirectory = File(context.cacheDir.parent)
    if (applicationDirectory.exists()) {
        val fileNames = applicationDirectory.list()
        for (fileName in fileNames) {
            if (fileName != "lib") {
                deleteFile(File(applicationDirectory, fileName))
            }
        }
    }
}

private fun deleteFile(file: File?): Boolean {
    var deletedAll = true
    if (file != null) {
        if (file.isDirectory) {
            val children = file.list()
            for (i in children.indices) {
                deletedAll = deleteFile(File(file, children[i])) && deletedAll
            }
        } else {
            deletedAll = file.delete()
        }
    }
    return deletedAll
}