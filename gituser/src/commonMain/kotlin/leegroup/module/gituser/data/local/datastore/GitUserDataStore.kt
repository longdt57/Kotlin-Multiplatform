package leegroup.module.gituser.data.local.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.Flow
import leegroup.module.data.datastore.BaseDataStore

private const val APP_DATASTORE = "git-user-datastore"

internal class GitUserDataStore() : BaseDataStore(APP_DATASTORE) {

    fun getAppPreference(): Flow<Boolean?> {
        return getValue(APP_PREFERENCE)
    }

    suspend fun setAppPreference(value: Boolean) {
        setValue(APP_PREFERENCE, value)
    }

    companion object {
        private val APP_PREFERENCE = booleanPreferencesKey("APP_PREFERENCE")
    }
}