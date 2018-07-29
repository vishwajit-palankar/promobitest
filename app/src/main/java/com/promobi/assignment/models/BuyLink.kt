package com.promobi.assignment.models

import com.google.gson.annotations.SerializedName

data class BuyLink(
        @SerializedName("name") var name: String,
        @SerializedName("url") var url: String
)