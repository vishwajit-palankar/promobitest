package com.promobi.assignment.api

import com.promobi.assignment.MainActivity
import dagger.Component

/**
 * Created by Vishwajit on 30/07/18.
 */

@Component(modules = [(ApiModule::class)])
interface MyComponent {

    fun inject(main: MainActivity)
}