package leegroup.module.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import okio.Path
import okio.Path.Companion.toPath
import org.koin.java.KoinJavaComponent.getKoin

class AndroidCorePlatform(
    private val context: Context,
) : CorePlatform {
    override fun dataStorePath(name: String): Path {
        return context.filesDir.resolve(name).absolutePath.toPath()
    }

    override fun createHttpClient(): HttpClient {
        return HttpClient(OkHttp)
    }

}

actual inline fun <reified T : RoomDatabase> getDatabaseBuilder(name: String): RoomDatabase.Builder<T> {
    val appContext = getKoin().get<Context>().applicationContext
    val dbFile = appContext.getDatabasePath(name)
    return Room.databaseBuilder<T>(
        context = appContext,
        name = dbFile.absolutePath
    )
}

actual fun getCorePlatform(): CorePlatform = AndroidCorePlatform(getKoin().get<Context>())