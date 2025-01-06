package leegroup.app.kmm.gituser.di

import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(sharedModule)
}

@Suppress("unused")
fun initializeKoin() {
    initKoin()
}
