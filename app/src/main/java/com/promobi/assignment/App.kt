package com.promobi.assignment

import android.app.Activity
import android.app.Application
import com.promobi.assignment.di.ApiModule
import com.promobi.assignment.di.AppModule
import com.promobi.assignment.di.DaggerMyComponent
import com.promobi.assignment.util.Constants
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Vishwajit on 30/07/18.
 */
class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerMyComponent.builder()
                .appModule(AppModule(this))
                .apiModule(ApiModule(Constants.BASE_URL))
                .build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}