package leegroup.app.kmm.gituser

import leegroup.app.kmm.gituser.di.appModule
import leegroup.app.kmm.gituser.di.iOSModule
import leegroup.module.gituser.di.gitModule
import org.koin.core.context.startKoin

fun initKoinIos() {
    startKoin {
        modules(gitModule)
        modules(appModule)
        modules(iOSModule)
    }
}