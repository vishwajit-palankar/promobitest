package com.promobi.assignment.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
        @SerializedName("age_group") var ageGroup: String,
        @SerializedName("amazon_product_url") var amazonProductUrl: String,
        @SerializedName("article_chapter_link") var articleChapterLink: String,
        @SerializedName("author") var author: String,
        @SerializedName("book_image") var bookImage: String,
        @SerializedName("book_image_width") var bookImageWidth: Int,
        @SerializedName("book_image_height") var bookImageHeight: Int,
        @SerializedName("book_review_link") var bookReviewLink: String,
        @SerializedName("contributor") var contributor: String,
        @SerializedName("contributor_note") var contributorNote: String,
        @SerializedName("created_date") var createdDate: String,
        @SerializedName("description") var description: String,
        @SerializedName("first_chapter_link") var firstChapterLink: String,
        @SerializedName("price") var price: Int,
        @SerializedName("primary_isbn10") var primaryIsbn10: String,
        @SerializedName("primary_isbn13") var primaryIsbn13: String,
        @SerializedName("publisher") var publisher: String,
        @SerializedName("rank") var rank: Int,
        @SerializedName("rank_last_week") var rankLastWeek: Int,
        @SerializedName("sunday_review_link") var sundayReviewLink: String,
        @SerializedName("title") var title: String,
        @SerializedName("updated_date") var updatedDate: String,
        @SerializedName("weeks_on_list") var weeksOnList: Int,
        @SerializedName("buy_links") var buyLinks: List<BuyLink>
):Parcelable