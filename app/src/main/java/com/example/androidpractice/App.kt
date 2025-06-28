package com.example.androidpractice

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.androidpractice.di.networkModule
import com.example.androidpractice.di.rootModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(rootModule, networkModule)
        }
    }
}