package com.astro.test.rezafahrizal.module

import android.content.Context
import com.astro.test.rezafahrizal.repository.AppDatabase
import org.koin.dsl.module

val repositoryModule = module {
    single { appDatabase(get()) }
}

private fun appDatabase(context: Context) = AppDatabase.getInstance(context).parametersDao()
