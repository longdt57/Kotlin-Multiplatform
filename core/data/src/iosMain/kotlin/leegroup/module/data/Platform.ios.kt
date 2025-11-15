package leegroup.module.data

import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

class IOSCorePlatform : CorePlatform {
    @OptIn(ExperimentalForeignApi::class)
    override fun dataStorePath(name: String): Path {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return (requireNotNull(documentDirectory).path + "/$name").toPath()
    }
}

actual fun getCorePlatform(): CorePlatform = IOSCorePlatform()