package com.oldsenior.ella.corelib.util

import com.oldsenior.ella.corelib.BuildConfig

enum class BuildType {
    DEBUG, RELEASE;

    val isCurrentBuild: Boolean
        get() = this.toString().toLowerCase().contentEquals(BuildConfig.BUILD_TYPE)

}
