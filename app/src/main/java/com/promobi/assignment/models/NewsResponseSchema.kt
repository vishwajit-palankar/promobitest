package com.promobi.assignment.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.promobi.assignment.data.local.TypeConverter

@Entity
data class NewsResponseSchema(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int,
        @ColumnInfo(name = "status") @SerializedName("status") var status: String,
        @ColumnInfo(name = "copyright") @SerializedName("copyright") var copyright: String,
        @ColumnInfo(name = "num_results") @SerializedName("num_results") var numResults: Int,
        @TypeConverters(TypeConverter::class)
        @ColumnInfo(name = "results") @SerializedName("results") var results: Results
)