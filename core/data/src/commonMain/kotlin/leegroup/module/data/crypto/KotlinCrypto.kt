package leegroup.module.data.crypto

import dev.whyoleg.cryptography.CryptographyProvider
import dev.whyoleg.cryptography.algorithms.AES
import io.ktor.util.decodeBase64Bytes
import io.ktor.util.encodeBase64

object KotlinCrypto {
    private val provider = CryptographyProvider.Default
    private val aesGcm = provider.get(AES.GCM)

    suspend fun generateAesKey(): String {
        val key = aesGcm.keyGenerator(keySize = AES.Key.Size.B256).generateKey()
        return key.encodeToByteArray(AES.Key.Format.RAW).encodeBase64()
    }

    suspend fun encrypt(value: String, keyBase64: String): String {
        val keyBytes = keyBase64.decodeBase64Bytes()
        val key = aesGcm.keyDecoder().decodeFromByteArray(AES.Key.Format.RAW, keyBytes)

        // Encrypt
        val cipher = key.cipher()
        val cipherText: ByteArray = cipher.encrypt(value.encodeToByteArray())
        return cipherText.encodeBase64()
    }

    suspend fun decrypt(cipherTextBase64: String, keyBase64: String): String {
        val keyBytes = keyBase64.decodeBase64Bytes()
        val cipherBytes = cipherTextBase64.decodeBase64Bytes()
        val key = aesGcm.keyDecoder().decodeFromByteArray(AES.Key.Format.RAW, keyBytes)
        val cipher = key.cipher()
        val plainBytes: ByteArray = cipher.decrypt(cipherBytes)
        return plainBytes.decodeToString()
    }
}