package com.llsparrow.healthassistant.feature_secure_storage_impl.cipher

interface SecureCipher {
    fun encrypt(data: ByteArray): ByteArray
    fun decrypt(data: ByteArray): ByteArray
}