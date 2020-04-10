package com.llsparrow.healthassistant.core_di.holder

interface FeatureContainer {
    open fun <T> getFeature(key: Class<*>): T
    open fun releaseFeature(key: Class<*>)
}
