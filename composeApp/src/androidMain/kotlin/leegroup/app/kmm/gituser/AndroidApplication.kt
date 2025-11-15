package leegroup.app.kmm.gituser

import android.app.Application
import leegroup.app.kmm.gituser.di.androidModule
import leegroup.app.kmm.gituser.di.appModule
import leegroup.module.gituser.di.gitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AndroidApplication)
            modules(gitModule)
            modules(appModule)
            modules(androidModule)
        }

        setupLogging()
    }

    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
