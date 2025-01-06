package leegroup.app.kmm.gituser

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform