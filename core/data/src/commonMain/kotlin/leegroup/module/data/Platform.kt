package leegroup.module.data

import okio.Path

interface CorePlatform {
    fun dataStorePath(name: String): Path

}

expect fun getCorePlatform(): CorePlatform