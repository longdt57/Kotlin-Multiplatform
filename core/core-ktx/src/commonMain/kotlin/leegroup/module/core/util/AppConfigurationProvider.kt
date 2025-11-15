package leegroup.module.core.util

interface AppConfigurationProvider {

    val debug: Boolean
    val buildType: String
    val flavor: String
    val versionCode: Int
    val versionName: String
}