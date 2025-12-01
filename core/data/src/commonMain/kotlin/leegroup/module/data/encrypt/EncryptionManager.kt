package leegroup.module.data.encrypt

import dev.whyoleg.cryptography.CryptographyProvider
import dev.whyoleg.cryptography.algorithms.AES
import dev.whyoleg.cryptography.algorithms.SHA256
import io.ktor.util.decodeBase64Bytes
import io.ktor.util.encodeBase64
import kotlinx.coroutines.runBlocking

object EncryptionManager {
    private val provider = CryptographyProvider.Default
    private val aesGcm = provider.get(AES.GCM)

    val defaultKey by lazy {
        runBlocking { generateKey() }
    }

    suspend fun generateKey(seed: String = "seed"): String {
        val seedBytes = seed.encodeToByteArray()

        // SHA-256 hash of seed -> 32 bytes (256 bits)
        val hash: ByteArray = provider.get(SHA256).hasher().hash(seedBytes)

        // Use full 32 bytes directly as AES-256 key material
        return hash.encodeBase64()
    }

    suspend fun generateRandomKey(): String {
        val key = aesGcm.keyGenerator(keySize = AES.Key.Size.B256).generateKey()
        return key.encodeToByteArray(AES.Key.Format.RAW).encodeBase64()
    }

    suspend fun encrypt(value: String, keyBase64: String = defaultKey): String {
        val keyBytes = keyBase64.decodeBase64Bytes()
        val key = aesGcm.keyDecoder().decodeFromByteArray(AES.Key.Format.RAW, keyBytes)

        // Encrypt
        val cipher = key.cipher()
        val cipherText: ByteArray = cipher.encrypt(value.encodeToByteArray())
        return cipherText.encodeBase64()
    }

    suspend fun decrypt(value: String, keyBase64: String = defaultKey): String {
        val keyBytes = keyBase64.decodeBase64Bytes()
        val cipherBytes = value.decodeBase64Bytes()
        val key = aesGcm.keyDecoder().decodeFromByteArray(AES.Key.Format.RAW, keyBytes)
        val cipher = key.cipher()
        val plainBytes: ByteArray = cipher.decrypt(cipherBytes)
        return plainBytes.decodeToString()
    }
}