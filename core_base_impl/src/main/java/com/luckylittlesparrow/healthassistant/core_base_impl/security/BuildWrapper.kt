package com.luckylittlesparrow.healthassistant.core_base_impl.security

import android.os.Build

class BuildWrapper {

    val brand: String = Build.BRAND

    val device: String = Build.DEVICE

    val fingerprint: String = Build.FINGERPRINT

    val manufacturer: String = Build.MANUFACTURER

    val model: String = Build.MODEL

    val product: String = Build.PRODUCT
}