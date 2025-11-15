package leegroup.module.designsystem

interface DesignPlatform {
    val name: String

    fun openUrl(url: String)
}

expect fun getDesignPlatform(): DesignPlatform