package com.astro.test.rezafahrizal.application

import android.app.Application
import com.astro.test.rezafahrizal.module.networkModule
import com.astro.test.rezafahrizal.module.repositoryModule
import com.astro.test.rezafahrizal.module.stateModule
import com.astro.test.rezafahrizal.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(listOf(networkModule, stateModule, viewModelModule, repositoryModule))
        }
    }
}