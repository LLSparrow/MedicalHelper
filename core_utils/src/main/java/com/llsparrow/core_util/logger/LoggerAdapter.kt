package com.llsparrow.core_util.logger

import com.llsparrow.healthassistant.core_util.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy

const val DEBUG = "debug"

class LoggerAdapter(strategy: FormatStrategy) : AndroidLogAdapter(strategy) {

    override fun isLoggable(priority: Int, tag: String?): Boolean {
        return BuildConfig.BUILD_TYPE == DEBUG
    }
}
