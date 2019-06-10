package com.oldsenior.ella.core_base_impl.util

import com.oldsenior.ella.core_base_impl.BuildConfig

enum class BuildType {
    DEBUG, RELEASE;

    val isCurrentBuild: Boolean
        get() = this.toString().toLowerCase().contentEquals(BuildConfig.BUILD_TYPE)

}
