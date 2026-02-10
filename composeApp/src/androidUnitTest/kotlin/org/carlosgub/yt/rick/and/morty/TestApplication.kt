package org.carlosgub.yt.rick.and.morty

import android.app.Application

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Do not initialize Koin here, let the tests handle it
    }
}
