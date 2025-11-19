package leegroup.module.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import leegroup.module.core.util.JsonUtil
import leegroup.module.data.DataPlatform

abstract class BaseDataStore(
    prefName: String,
) {

    protected open val dataStore: DataStore<Preferences> =
        PreferenceDataStoreFactory.createWithPath(
            produceFile = { DataPlatform.dataStorePath("$prefName.preferences_pb") }
        )

    protected fun <T> getValue(key: Preferences.Key<T>): Flow<T?> {
        return dataStore.data.map { preferences ->
            preferences[key]
        }
    }

    protected suspend fun <T> setValue(key: Preferences.Key<T>, value: T) {
        dataStore.edit { settings ->
            settings[key] = value
        }
    }

    protected fun <T> getListValue(key: Preferences.Key<String>): Flow<List<T>> {
        return dataStore.data.map { preferences ->
            val value = preferences[key] ?: return@map emptyList()
            JsonUtil.decodeFromString<List<T>>(value).orEmpty()
        }
    }

    protected suspend fun <T> setValue(key: Preferences.Key<String>, value: List<T>) {
        dataStore.edit { settings ->
            val jsonValue = JsonUtil.encodeToString(value)
            settings[key] = jsonValue
        }
    }

    protected inline fun <reified T> getJsonValue(key: Preferences.Key<String>): Flow<T?> {
        return dataStore.getJsonValue(key)
    }

    protected suspend inline fun <reified T : Any> setJsonValue(
        key: Preferences.Key<String>,
        value: T
    ) {
        dataStore.setJsonValue(key, value)
    }

    suspend fun clearAll() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
