package com.example.recipes

import android.app.Application
import com.example.recipes.di.module.appModule
import com.example.recipes.di.module.repoModule
import com.example.recipes.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}