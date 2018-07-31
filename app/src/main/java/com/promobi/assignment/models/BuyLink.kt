package com.promobi.assignment.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BuyLink(
        @SerializedName("name") var name: String,
        @SerializedName("url") var url: String
) : Parcelable