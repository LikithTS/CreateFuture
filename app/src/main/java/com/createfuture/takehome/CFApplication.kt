package com.createfuture.takehome

import android.app.Application
import com.createfuture.takehome.di.createFutureModule
import org.koin.core.context.startKoin

class CFApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Starting dependency injection
        startKoin {
            modules(createFutureModule)
        }
    }
}