package com.example.androidpractice.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.androidpractice.listWithDetails.data.API.MovieAPI
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideRetrofit(get()) }
    single { provideNetworkApi(get()) }
}

fun provideRetrofit(context: Context): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://rest.imdbapi.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().apply {
            addInterceptor {
                Interceptor { chain ->
                    val request: Request = chain.request()
                    val url: HttpUrl =
                        request.url.newBuilder().build()
                    chain.proceed(request.newBuilder().url(url).build())
                }.intercept(it)
            }
            addInterceptor(ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build())
        }.build())
        .build()

}

fun provideNetworkApi(retrofit: Retrofit): MovieAPI =
    retrofit.create(MovieAPI::class.java)