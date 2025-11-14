package leegroup.app.kmm.gituser

import leegroup.module.gituser.di.sharedModule
import org.koin.core.context.startKoin

fun initKoinIos() {
    startKoin {
        modules(sharedModule)
    }
}