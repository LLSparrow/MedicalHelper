package com.luckylittlesparrow.healthassistant.core_base_impl.date

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimeSourceImpl @Inject constructor() : TimeSource {

    override fun currentTimeMillis(): Long = System.currentTimeMillis()
}
