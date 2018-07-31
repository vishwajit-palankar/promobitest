package com.promobi.assignment.di

import com.google.gson.GsonBuilder
import com.promobi.assignment.data.remote.ApiService
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by Vishwajit on 29/07/18.
 */

@Module
class ApiModule(private val baseUrl: String) {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.hostnameVerifier { _, _ -> true }

        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)

        // Add headers
        builder.interceptors().add(Interceptor { chain ->
            val request = chain.request()
                    .newBuilder()
                    .build()

            chain.proceed(request)
        })

        // Logging
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.interceptors().add(interceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesGsonFactory(): GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())

    @Provides
    @Singleton
    fun providesRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory
            .createWithScheduler(Schedulers.io())

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory, rxAdapter: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxAdapter)
                .build()
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService =retrofit.create(ApiService::class.java)

}