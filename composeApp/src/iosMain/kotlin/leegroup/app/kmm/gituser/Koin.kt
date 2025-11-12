package leegroup.app.kmm.gituser

import leegroup.app.kmm.gituser.di.sharedModule
import org.koin.core.context.startKoin

fun initKoinIos() {
    startKoin {
        modules(sharedModule)
    }
}