package leegroup.module.data

import androidx.room.Room
import androidx.room.RoomDatabase
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import okio.Path
import okio.Path.Companion.toPath

actual object DataPlatform {
    actual fun dataStorePath(name: String): Path {
        return KmpApplication.application.filesDir.resolve(name).absolutePath.toPath()
    }

    actual fun createHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient {
        return HttpClient(OkHttp, block)
    }

    actual inline fun <reified T : RoomDatabase> getDatabaseBuilder(name: String): RoomDatabase.Builder<T> {
        val appContext = KmpApplication.application
        val dbFile = appContext.getDatabasePath(name)
        return Room.databaseBuilder<T>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }

}