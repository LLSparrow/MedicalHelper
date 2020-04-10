package com.llsparrow.healthassistant.core_feature_toggle_api.config

interface AppConfig {
    fun isDebug(): Boolean
    fun isDebugBuildType(): Boolean
    fun getPackage(): String
    fun getVersionCode(): Int
    fun getVersionName(): String
    fun apiVersion(): String
    fun appName(): String
}