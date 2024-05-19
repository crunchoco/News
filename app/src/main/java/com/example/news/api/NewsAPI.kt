package com.example.news.api

import com.example.news.models.NewsResponse
import com.example.news.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("https://newsapi.org/v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apikey")
        apikey: String =API_KEY
    ): Response<NewsResponse>

    @GET("https://newsapi.org/v2/everything")
    suspend fun serachForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apikey")
        apikey: String = API_KEY
    ):Response<NewsResponse>
}