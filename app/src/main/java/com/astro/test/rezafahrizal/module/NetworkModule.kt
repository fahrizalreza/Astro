package com.astro.test.rezafahrizal.module

import android.content.Context
import com.astro.test.rezafahrizal.services.ApiService
import com.astro.test.rezafahrizal.utilities.AppConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { okHttp(get()) }

    single { retrofit(AppConfig.URL) }

    single { connect(get()) }
}

private fun okHttp(context: Context): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(OkHttpProfilerInterceptor())
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)

    return okHttpClient.build()
}

private fun retrofit(baseUrl: String) = Retrofit.Builder()
    .callFactory(OkHttpClient.Builder().build())
    .baseUrl(baseUrl)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .build()

private fun connect(context: Context): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(AppConfig.URL)
        .client(okHttp(context))
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}
