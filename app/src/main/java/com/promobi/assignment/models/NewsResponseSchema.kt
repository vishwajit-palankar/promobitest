package com.promobi.assignment.models

import com.google.gson.annotations.SerializedName

data class NewsResponseSchema(
        @SerializedName("status") var status: String,
        @SerializedName("copyright") var copyright: String,
        @SerializedName("num_results") var numResults: Int,
        @SerializedName("results") var results: Results
)