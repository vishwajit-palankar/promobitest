package com.promobi.assignment.models

import com.google.gson.annotations.SerializedName

data class Lists(
        @SerializedName("list_id") var listId: Int,
        @SerializedName("list_name") var listName: String,
        @SerializedName("list_name_encoded") var listNameEncoded: String,
        @SerializedName("display_name") var displayName: String,
        @SerializedName("updated") var updated: String,
        @SerializedName("list_image") var listImage: String,
        @SerializedName("list_image_width") var listImageWidth: Int,
        @SerializedName("list_image_height") var listImageHeight: Int,
        @SerializedName("books") var books: List<Book>
)