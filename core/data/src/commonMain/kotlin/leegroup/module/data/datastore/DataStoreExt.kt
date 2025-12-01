package leegroup.module.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import leegroup.module.core.util.JsonUtil
import leegroup.module.data.encrypt.EncryptionManager

fun <T> DataStore<Preferences>.getValue(key: Preferences.Key<T>): Flow<T?> {
    return data.map { preferences ->
        preferences[key]
    }
}

suspend fun <T> DataStore<Preferences>.setValue(key: Preferences.Key<T>, value: T) {
    edit { settings ->
        settings[key] = value
    }
}

inline fun <reified T> DataStore<Preferences>.getJsonValue(key: Preferences.Key<String>): Flow<T?> {
    return data.map { preferences ->
        preferences[key]
    }
        .map { JsonUtil.decodeFromString(it.orEmpty()) }
}

suspend inline fun <reified T : Any> DataStore<Preferences>.setJsonValue(
    key: Preferences.Key<String>,
    value: T
) {
    edit { settings ->
        settings[key] = JsonUtil.encodeToString(value)
    }
}

inline fun <reified T> Preferences.getJsonValue(key: Preferences.Key<String>): T? {
    return JsonUtil.decodeFromString(this[key].orEmpty())
}

inline fun <reified T : Any> MutablePreferences.setJsonValue(
    key: Preferences.Key<String>,
    value: T
) {
    this[key] = JsonUtil.encodeToString(value)
}

suspend inline fun <reified T : Any> DataStore<Preferences>.setEncryptedJsonValue(
    key: Preferences.Key<String>,
    value: T,
    encryptedKey: String = EncryptionManager.defaultKey
) {
    JsonUtil.encodeToString(value).let {
        EncryptionManager.encrypt(value = it, keyBase64 = encryptedKey)
    }.let {
        setValue(key, it)
    }
}

inline fun <reified T> DataStore<Preferences>.getEncryptedJsonValue(
    key: Preferences.Key<String>,
    encryptedKey: String = EncryptionManager.defaultKey
): Flow<T?> {
    return getValue(key)
        .map { value ->
            value?.let {
                EncryptionManager.decrypt(value = it, keyBase64 = encryptedKey)
            }
        }
        .map { JsonUtil.decodeFromString(it.orEmpty()) }
}