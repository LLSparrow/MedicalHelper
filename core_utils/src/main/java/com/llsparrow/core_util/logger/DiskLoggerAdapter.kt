package com.llsparrow.core_util.logger

import com.llsparrow.healthassistant.core_util.BuildConfig
import com.orhanobut.logger.DiskLogAdapter

class DiskLoggerAdapter : DiskLogAdapter() {

    override fun isLoggable(priority: Int, tag: String?): Boolean {
        return BuildConfig.BUILD_TYPE == DEBUG
    }
}
