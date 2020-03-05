package com.luckylittlesparrow.healthassistant.core_base_impl.date

interface TimeSource {
    fun currentTimeMillis(): Long
}