package com.promobi.assignment.data.remote

import com.promobi.assignment.models.NewsResponseSchema
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("svc/books/v3/lists/overview.json")
    fun getNewsList(@Query("api-key") apiKey: String?): Observable<NewsResponseSchema>
}
