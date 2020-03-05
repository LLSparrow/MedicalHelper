package com.luckylittlesparrow.healthassistant.core_base_impl.security

/**
 * @author Gusev Andrei
 * @since  1.0
 */
class EmulatorVeryfier(private val build: BuildWrapper = BuildWrapper()) {

    fun isEmulator(): Boolean {
        return (build.fingerprint.startsWith("generic")
                || build.fingerprint.startsWith("unknown")
                || build.model.contains("google_sdk")
                || build.model.contains("Emulator")
                || build.model.contains("Android SDK built for x86")
                || build.manufacturer.contains("Genymotion")
                || build.brand.startsWith("generic") && build.device.startsWith("generic")
                || "google_sdk" == build.product)
    }
}