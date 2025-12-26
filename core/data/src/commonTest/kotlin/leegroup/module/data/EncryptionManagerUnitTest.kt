package leegroup.module.data

import kotlinx.coroutines.test.runTest
import leegroup.module.data.encrypt.EncryptionManager
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class EncryptionManagerTest {

    @Test
    fun generateKey_isDeterministic_forSameSeed() = runTest {
        val k1 = EncryptionManager.generateKey("seed123")
        val k2 = EncryptionManager.generateKey("seed123")
        assertEquals(k1, k2)
        assertTrue(k1.isNotBlank())
    }

    @Test
    fun generateKey_differs_forDifferentSeeds() = runTest {
        val k1 = EncryptionManager.generateKey("seedA")
        val k2 = EncryptionManager.generateKey("seedB")
        assertNotEquals(k1, k2)
    }

    @Test
    fun generateRandomKey_returnsNonEmptyBase64() = runTest {
        val k = EncryptionManager.generateRandomKey()
        assertTrue(k.isNotBlank())
        // 32 bytes raw -> Base64 length typically 44 with padding, but don’t hard-fail on variant
        assertTrue(k.length >= 40)
    }

    @Test
    fun encrypt_decrypt_roundTrip_withExplicitKey() = runTest {
        val key = EncryptionManager.generateKey("my-seed")
        val plain = "Hello KMP AES-GCM!"

        val encrypted = EncryptionManager.encrypt(plain, key)
        val decrypted = EncryptionManager.decrypt(encrypted, key)

        assertEquals(plain, decrypted)
    }

    @Test
    fun encrypt_decrypt_roundTrip_withDefaultKey() = runTest {
        val plain = "Default key roundtrip"
        val encrypted = EncryptionManager.encrypt(plain)   // uses defaultKey
        val decrypted = EncryptionManager.decrypt(encrypted)

        assertEquals(plain, decrypted)
    }

    @Test
    fun encrypt_samePlaintext_twice_producesDifferentCiphertext() = runTest {
        val key = EncryptionManager.generateKey("seed123")
        val plain = "same text"

        val c1 = EncryptionManager.encrypt(plain, key)
        val c2 = EncryptionManager.encrypt(plain, key)

        // AES-GCM should use a random nonce => ciphertext should differ
        assertNotEquals(c1, c2)
    }

    @Test
    fun decrypt_withWrongKey_throws() = runTest {
        val rightKey = EncryptionManager.generateKey("right")
        val wrongKey = EncryptionManager.generateKey("wrong")

        val encrypted = EncryptionManager.encrypt("secret", rightKey)

        assertFailsWith<Throwable> {
            EncryptionManager.decrypt(encrypted, wrongKey)
        }
    }

    @Test
    fun decrypt_invalidCiphertext_throws() = runTest {
        val key = EncryptionManager.generateKey("seed123")

        assertFailsWith<Throwable> {
            EncryptionManager.decrypt("not-base64!!!", key)
        }
    }
}