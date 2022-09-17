package com.example.recipes.di.module

import android.content.Context
import com.example.recipes.BuildConfig
import com.example.recipes.data.api.ApiHelper
import com.example.recipes.data.api.ApiHelperImpl
import com.example.recipes.data.api.ApiService
import com.example.recipes.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.spoonacular.com/"

val appModule = module {
    factory { provideOkHttpClient() }
    factory { provideRetrofit(get(), BASE_URL) }
    factory{ get<Retrofit>().create(ApiService::class.java) }
    //factory { provideApiService(get()) }
    factory { provideNetworkHelper(androidContext()) }

    factory<ApiHelper> {
        return@factory ApiHelperImpl(get())
    }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

/*private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)*/