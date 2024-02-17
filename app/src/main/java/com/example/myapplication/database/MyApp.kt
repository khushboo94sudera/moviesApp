package com.example.myapplication.database

import android.app.Application
import com.example.myapplication.di.appModule
import com.example.myapplication.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModule,vmModule)
        }
    }
}