package leegroup.app.kmm.gituser.di

import leegroup.app.kmm.gituser.provideAppConfig
import leegroup.module.core.util.AppConfigurationProvider
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    single<AppConfigurationProvider> { provideAppConfig() }
}
