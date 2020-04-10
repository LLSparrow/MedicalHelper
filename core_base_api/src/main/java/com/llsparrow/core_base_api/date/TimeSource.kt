package com.llsparrow.core_base_api.date

interface TimeSource {
    fun currentTimeMillis(): Long
}