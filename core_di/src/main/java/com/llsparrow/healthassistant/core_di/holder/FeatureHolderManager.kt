package com.llsparrow.healthassistant.core_di.holder

class FeatureHolderManager(private val featureHolders: Map<Class<*>, FeatureApiHolder>) {
    fun <T> getFeature(key: Class<*>): T {
        val featureApiHolder = featureHolders[key] ?: throw IllegalStateException()
        return featureApiHolder.getFeatureApi()
    }

    fun releaseFeature(key: Class<*>) {
        val featureApiHolder = featureHolders[key] ?: throw IllegalStateException()
        featureApiHolder.releaseFeatureApi()
    }

}