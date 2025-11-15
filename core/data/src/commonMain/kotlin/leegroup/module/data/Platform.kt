package leegroup.module.data

import androidx.room.RoomDatabase
import okio.Path

interface CorePlatform {
    fun dataStorePath(name: String): Path

}

expect inline fun <reified T : RoomDatabase> getDatabaseBuilder(name: String): RoomDatabase.Builder<T>

expect fun getCorePlatform(): CorePlatform