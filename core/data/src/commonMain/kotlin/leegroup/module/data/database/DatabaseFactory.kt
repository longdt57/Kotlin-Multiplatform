package leegroup.module.data.database

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import leegroup.module.data.DataPlatform.getDatabaseBuilder

object DatabaseFactory {
    inline fun <reified T : RoomDatabase> createRoomDatabase(name: String): T {
        return getDatabaseBuilder<T>(name)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }
}