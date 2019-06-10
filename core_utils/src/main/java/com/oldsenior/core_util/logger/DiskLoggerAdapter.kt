package com.oldsenior.core_util.logger

import com.oldsenior.core_util.BuildConfig
import com.orhanobut.logger.DiskLogAdapter

class DiskLoggerAdapter : DiskLogAdapter() {

    override fun isLoggable(priority: Int, tag: String?): Boolean {
        return BuildConfig.BUILD_TYPE == "debug"
    }
}
