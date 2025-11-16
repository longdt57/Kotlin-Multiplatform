package leegroup.module.data

import androidx.room.RoomDatabase
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import okio.Path

interface CorePlatform {
    fun dataStorePath(name: String): Path
    fun createHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient

}

expect inline fun <reified T : RoomDatabase> getDatabaseBuilder(name: String): RoomDatabase.Builder<T>

expect fun getCorePlatform(): CorePlatform