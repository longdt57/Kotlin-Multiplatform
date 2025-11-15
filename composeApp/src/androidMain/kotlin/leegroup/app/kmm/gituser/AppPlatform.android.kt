package leegroup.app.kmm.gituser

import leegroup.module.core.util.AppConfigurationProvider

actual fun provideAppConfig(): AppConfigurationProvider = object : AppConfigurationProvider {
    override val debug: Boolean
        get() = BuildConfig.DEBUG
    override val buildType: String
        get() = BuildConfig.BUILD_TYPE
    override val flavor: String
        get() = "BuildConfig.FLAVOR"
    override val versionCode: Int
        get() = BuildConfig.VERSION_CODE
    override val versionName: String
        get() = BuildConfig.VERSION_NAME

}