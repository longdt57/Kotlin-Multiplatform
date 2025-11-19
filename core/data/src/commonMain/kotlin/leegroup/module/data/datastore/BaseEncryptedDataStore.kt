package leegroup.module.data.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import leegroup.module.core.util.JsonUtil
import leegroup.module.data.crypto.KotlinCrypto

abstract class BaseEncryptedDataStore(
    prefName: String
) : BaseDataStore(prefName) {

    // encodeBase64 key
    protected suspend fun getEncryptedKey(): String = getValue(KEY_AES).firstOrNull()
        .takeUnless { it.isNullOrBlank() }
        ?: KotlinCrypto.generateAesKey().also { newKey ->
            setValue(KEY_AES, newKey)
        }

    protected suspend inline fun <reified T : Any> setEncryptedJsonValue(
        key: Preferences.Key<String>,
        value: T
    ) {
        JsonUtil.encodeToString(value).let {
            KotlinCrypto.encrypt(it, getEncryptedKey())
        }.let {
            setValue(key, it)
        }
    }

    protected inline fun <reified T> getEncryptedJsonValue(key: Preferences.Key<String>): Flow<T?> {
        return getValue(key)
            .map { value ->
                value?.let {
                    KotlinCrypto.decrypt(it, getEncryptedKey())
                }
            }
            .map { value -> value?.let { value -> JsonUtil.decodeFromString(value) } }
    }

    companion object Companion {
        private val KEY_AES = stringPreferencesKey("aes_key")
    }
}