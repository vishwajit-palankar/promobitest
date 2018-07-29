package com.promobi.assignment.models

import com.google.gson.annotations.SerializedName

data class Results(
        @SerializedName("bestsellers_date") var bestsellersDate: String,
        @SerializedName("published_date") var publishedDate: String,
        @SerializedName("published_date_description") var publishedDateDescription: String,
        @SerializedName("previous_published_date") var previousPublishedDate: String,
        @SerializedName("next_published_date") var nextPublishedDate: String,
        @SerializedName("lists") var lists: List<Lists>
)