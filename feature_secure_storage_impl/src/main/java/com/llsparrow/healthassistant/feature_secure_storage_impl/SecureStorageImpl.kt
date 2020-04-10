package com.llsparrow.healthassistant.feature_secure_storage_impl

import android.util.Base64
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_secure_storage_api.SecureStorage
import com.llsparrow.healthassistant.feature_secure_storage_impl.cipher.SecureCipher
import com.llsparrow.healthassistant.feature_secure_storage_impl.storage.Storage
import javax.inject.Inject

@FeatureScope
class SecureStorageImpl @Inject constructor(
    private val storage: Storage,
    private val secureCipher: SecureCipher
) : SecureStorage {

    override fun save(key: String, data: String) {
        save(key, data.toByteArray())
    }

    override fun save(key: String, data: ByteArray) {
        val encryptedData = secureCipher.encrypt(data)
        storage.save(key, Base64.encodeToString(encryptedData, Base64.DEFAULT))
    }

    override fun retrieveString(key: String): String? {
        val data = retrieve(key) ?: return null
        return String(data)
    }

    override fun retrieve(key: String): ByteArray? {
        val encryptedString = storage.retrieve(key)
        return if (encryptedString != null) {
            secureCipher.decrypt(Base64.decode(encryptedString, Base64.DEFAULT))
        } else {
            null
        }
    }

    override fun clear() {
        storage.clear()
    }
}