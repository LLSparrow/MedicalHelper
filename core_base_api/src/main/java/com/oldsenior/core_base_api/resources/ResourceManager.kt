package com.oldsenior.core_base_api.resources

import androidx.annotation.StringRes

interface ResourceManager {
    fun getString(@StringRes resource: Int): String
}