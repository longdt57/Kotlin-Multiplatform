package leegroup.module.data

import androidx.room.RoomDatabase
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import okio.Path

expect object DataPlatform {
    fun dataStorePath(name: String): Path
    fun createHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient

    inline fun <reified T : RoomDatabase> getDatabaseBuilder(name: String): RoomDatabase.Builder<T>
}
