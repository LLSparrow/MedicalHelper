package com.llsparrow.healthassistant.core_base_impl.security

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Base64
import com.orhanobut.logger.Logger
import java.security.MessageDigest

private val PLAY_STORE_APP_ID = "com.android.vending"

class AppVeryfier {

    private val SIGNATURE = "478yYkKAQF+KST8y4ATKvHkYibo="

    fun checkAppSignature(context: Context): Boolean {
        runCatching {
            val packageInfo = context.packageManager
                .getPackageInfo(
                    context.packageName,
                    PackageManager.GET_SIGNATURES
                )

            for (signature in packageInfo.signatures) {

                val signatureBytes = signature.toByteArray();

                val md = MessageDigest.getInstance("SHA");

                md.update(signature.toByteArray());

                val currentSignature = Base64.encodeToString(md.digest(), Base64.DEFAULT);

                Logger.d("Include this string as a value for SIGNATURE:$currentSignature");

                if (SIGNATURE == currentSignature) {
                    return true
                }
            }

        }
        return false
    }


    fun verifyInstaller(context: Context): Boolean {
        val installer = context.packageManager.getInstallerPackageName(context.packageName)

        return installer != null && installer.startsWith(PLAY_STORE_APP_ID)
    }

    fun checkDebuggable(context: Context): Boolean {
        return (context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
    }
}