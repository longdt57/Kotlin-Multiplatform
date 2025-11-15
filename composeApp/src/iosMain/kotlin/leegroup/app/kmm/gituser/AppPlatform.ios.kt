package leegroup.app.kmm.gituser

import leegroup.module.core.util.AppConfigurationProvider

actual fun provideAppConfig(): AppConfigurationProvider {
    return object : AppConfigurationProvider {
        override val debug = true
        override val buildType = "release"
        override val flavor = "ios"
        override val versionCode = 1
        override val versionName = "1.0.0"
    }
}