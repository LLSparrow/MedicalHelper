package com.oldsenior.core_util.logger

import com.oldsenior.core_util.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy

class LoggerAdapter(strategy: FormatStrategy) : AndroidLogAdapter(strategy) {

    override fun isLoggable(priority: Int, tag: String?): Boolean {
        return BuildConfig.BUILD_TYPE == "debug"
    }
}
