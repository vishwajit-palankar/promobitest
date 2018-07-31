package com.promobi.assignment.models

import android.arch.lifecycle.ViewModel
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.promobi.assignment.database.TypeConverter

@Entity
data class Results(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") var id: Int,
        @ColumnInfo(name = "bestsellers_date") @SerializedName("bestsellers_date") var bestsellersDate: String,
        @ColumnInfo(name = "published_date") @SerializedName("published_date") var publishedDate: String,
        @ColumnInfo(name = "published_date_description") @SerializedName("published_date_description") var publishedDateDescription: String,
        @ColumnInfo(name = "previous_published_date") @SerializedName("previous_published_date") var previousPublishedDate: String,
        @ColumnInfo(name = "next_published_date") @SerializedName("next_published_date") var nextPublishedDate: String,
        @TypeConverters(TypeConverter::class) @ColumnInfo(name = "lists") @SerializedName("lists") var lists: List<Lists>
): ViewModel()