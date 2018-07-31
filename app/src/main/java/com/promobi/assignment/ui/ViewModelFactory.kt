package com.promobi.assignment.ui

import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by Vishwajit on 01/08/18.
 */
class ViewModelFactory @Inject constructor(
        private val viewModel: ViewModel) : ViewModelProvider.Factory {

    override fun <T : android.arch.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModel::class.java)) {
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}