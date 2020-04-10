package com.llsparrow.healthassistant.feature_secure_storage_impl.cipher

import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.llsparrow.healthassistant.core_di.FeatureScope
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.util.*
import javax.crypto.Cipher
import javax.inject.Inject
import javax.security.auth.x500.X500Principal

@FeatureScope
class SecureCipherImpl @Inject constructor(
    private val context: Context
) : SecureCipher {

    companion object {
        private const val KEY_STORE_PROVIDER = "AndroidKeyStore"
        private const val TYPE_RSA = "RSA"
        private const val TRANSFORMATION = "RSA/ECB/PKCS1Padding"

        private const val SERIAL_NUMBER = 12345678L

        private const val APP_KEY = "medical_helper_key"
    }

    private lateinit var keyStore: KeyStore

    init {
        createKeyStore()
    }

    override fun encrypt(data: ByteArray): ByteArray {
        val privateKeyEntry = keyStore.getEntry(APP_KEY, null) as KeyStore.PrivateKeyEntry
        val privateKey = privateKeyEntry.privateKey
        val publicKey = privateKeyEntry.certificate.publicKey
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        return cipher.doFinal(data)
    }

    override fun decrypt(data: ByteArray): ByteArray {
        val privateKeyEntry = keyStore.getEntry(APP_KEY, null) as KeyStore.PrivateKeyEntry
        val privateKey = privateKeyEntry.privateKey
        val publicKey = privateKeyEntry.certificate.publicKey
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        return cipher.doFinal(data)
    }

    private fun createKeyStore() {
        keyStore = KeyStore.getInstance(KEY_STORE_PROVIDER)
        keyStore.load(null)
        if (keyStore.getKey(APP_KEY, null) == null) {
            createKeys()
        }
    }

    private fun createKeys() {
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.YEAR, 25)

        val spec = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            KeyPairGeneratorSpec.Builder(context)
                .setAlias(SecureCipherImpl.APP_KEY)
                .setSubject(X500Principal("CN=$APP_KEY"))
                .setSerialNumber(BigInteger.valueOf(SERIAL_NUMBER))
                .setStartDate(startDate.time)
                .setEndDate(endDate.time)
                .build()
        } else {
            KeyGenParameterSpec.Builder(
                    SecureCipherImpl.APP_KEY,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                .setCertificateSubject(X500Principal("CN=$APP_KEY"))
                .setCertificateSerialNumber(BigInteger.valueOf(SERIAL_NUMBER))
                .setKeyValidityStart(startDate.time)
                .setKeyValidityEnd(endDate.time)
                .build()
        }
        val keyPairGenerator =
            KeyPairGenerator.getInstance(SecureCipherImpl.TYPE_RSA, SecureCipherImpl.KEY_STORE_PROVIDER)
        keyPairGenerator.initialize(spec)
        keyPairGenerator.generateKeyPair()
    }
}