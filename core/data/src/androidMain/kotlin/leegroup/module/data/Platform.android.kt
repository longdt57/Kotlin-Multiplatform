package leegroup.module.data

import android.content.Context
import okio.Path
import okio.Path.Companion.toPath
import org.koin.java.KoinJavaComponent.getKoin

class AndroidCorePlatform(
    private val context: Context,
) : CorePlatform {
    override fun dataStorePath(name: String): Path {
        return context.filesDir.resolve(name).absolutePath.toPath()
    }

}

actual fun getCorePlatform(): CorePlatform = AndroidCorePlatform(getKoin().get<Context>())