package com.promobi.assignment.di

import com.promobi.assignment.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Vishwajit on 30/07/18.
 */

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (BuildersModule::class), (ApiModule::class), (AppModule::class)])
interface MyComponent {

    fun inject(app: App)
}