package org.carlosgub.yt.rick.and.morty

import android.app.Application
import org.carlosgub.yt.rick.and.morty.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@MainApplication)
        }
    }
}
