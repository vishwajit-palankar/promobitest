package com.promobi.assignment.api

import com.google.gson.GsonBuilder
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



/**
 * Created by Vishwajit on 29/07/18.
 */

@Module
class ApiModule {

    @Provides
    fun providesApiService(): ApiService {

        val API_URL = "https://api.nytimes.com/"
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

        val rxAdapter = RxJava2CallAdapterFactory
                .createWithScheduler(Schedulers.io())

        val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(rxAdapter)
                .build()

        return retrofit.create(ApiService::class.java)
    }
}