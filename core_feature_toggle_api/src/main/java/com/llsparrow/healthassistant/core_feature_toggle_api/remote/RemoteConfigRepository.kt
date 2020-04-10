package com.llsparrow.healthassistant.core_feature_toggle_api.remote

import io.reactivex.Single

interface RemoteConfigRepository {
    fun getString(key: String): Single<String>
    fun getBoolean(key: String): Single<Boolean>
}