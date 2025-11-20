package ai.naver.carelink

import ai.naver.carelink.di.appModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CareLinkApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CareLinkApplication)
            modules(appModule)
        }
    }
}