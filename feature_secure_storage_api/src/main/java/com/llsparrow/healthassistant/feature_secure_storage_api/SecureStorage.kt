package com.llsparrow.healthassistant.feature_secure_storage_api

interface SecureStorage {

    fun save(key: String, data: String)
    fun save(key: String, data: ByteArray)

    fun retrieveString(key: String): String?
    fun retrieve(key: String): ByteArray?

    fun clear()
}