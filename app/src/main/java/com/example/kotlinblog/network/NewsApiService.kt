package com.example.kotlinblog.network

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String
    ): Map<String, String>

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String
    ): Map<String, String>

}