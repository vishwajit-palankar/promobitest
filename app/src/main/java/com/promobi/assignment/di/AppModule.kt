package com.promobi.assignment.di

import com.promobi.assignment.App
import com.promobi.assignment.data.local.AppDatabase
import com.promobi.assignment.data.local.DatabaseDao
import com.promobi.assignment.util.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vishwajit on 30/07/18.
 */
@Module
class AppModule(var application: App) {

    @Provides
    @Singleton
    fun provideApplication(): App = application

    @Provides
    @Singleton
    fun provideDatabase(app: App): AppDatabase = AppDatabase.getDatabase(app)

    @Provides
    @Singleton
    fun provideDatabaseDao(database: AppDatabase): DatabaseDao = database.databaseDao()

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(application)
}