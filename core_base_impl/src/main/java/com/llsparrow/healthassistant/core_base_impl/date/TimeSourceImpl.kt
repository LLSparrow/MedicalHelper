package com.llsparrow.healthassistant.core_base_impl.date

import com.llsparrow.core_base_api.date.TimeSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimeSourceImpl @Inject constructor() : TimeSource {

    override fun currentTimeMillis(): Long = System.currentTimeMillis()
}
