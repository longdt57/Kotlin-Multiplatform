package leegroup.app.kmm.gituser

import leegroup.module.gituser.di.gitModule
import org.koin.core.context.startKoin

fun initKoinIos() {
    startKoin {
        modules(gitModule)
    }
}