package com.promobi.assignment.database

import com.promobi.assignment.App
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
    fun providesApplication(): App {
        return application
    }


}