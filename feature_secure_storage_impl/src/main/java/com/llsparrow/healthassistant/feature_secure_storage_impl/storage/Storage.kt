package com.llsparrow.healthassistant.feature_secure_storage_impl.storage

interface Storage {
    fun save(key: String, data: String)
    fun retrieve(key: String): String?
    fun clear()
}