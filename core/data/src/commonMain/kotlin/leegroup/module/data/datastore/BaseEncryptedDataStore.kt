package leegroup.module.data.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import leegroup.module.data.encrypt.EncryptionManager

abstract class BaseEncryptedDataStore(
    prefName: String
) : BaseDataStore(prefName) {

    // encodeBase64 key
    protected val encryptedKey: String by lazy {
        runBlocking {
            getValue(KEY_ENCRYPTED).firstOrNull()
                .takeUnless { it.isNullOrBlank() }
                ?: EncryptionManager.generateRandomKey().also { newKey ->
                    setValue(KEY_ENCRYPTED, newKey)
                }
        }
    }

    protected suspend inline fun <reified T : Any> setEncryptedJsonValue(
        key: Preferences.Key<String>,
        value: T
    ) {
        dataStore.setEncryptedJsonValue(
            key = key,
            value = value,
            encryptedKey = encryptedKey
        )
    }

    protected inline fun <reified T> getEncryptedJsonValue(key: Preferences.Key<String>): Flow<T?> {
        return dataStore.getEncryptedJsonValue(key = key, encryptedKey = encryptedKey)
    }

    companion object Companion {
        private val KEY_ENCRYPTED = stringPreferencesKey("encrypted_key")
    }
}