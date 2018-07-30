package com.promobi.assignment.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "newsData")
data class Results(
        @ColumnInfo(name = "bestsellers_date") @SerializedName("bestsellers_date") var bestsellersDate: String,
        @ColumnInfo(name = "published_date") @SerializedName("published_date") var publishedDate: String,
        @ColumnInfo(name = "published_date_description") @SerializedName("published_date_description") var publishedDateDescription: String,
        @ColumnInfo(name = "previous_published_date") @SerializedName("previous_published_date") var previousPublishedDate: String,
        @ColumnInfo(name = "next_published_date") @SerializedName("next_published_date") var nextPublishedDate: String,
        @ColumnInfo(name = "lists") @SerializedName("lists") var lists: List<Lists>
)