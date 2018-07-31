package com.promobi.assignment.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by Vishwajit on 31/07/18.
 */
//class DataBindingUtil {


    @BindingAdapter("bind:imageUrl")
    fun ImageView.setImageUrl(imageUrl: String){
        Glide.with(context).load(imageUrl).into(this)
    }

//}