package com.example.kotlinblog.network

import com.example.kotlinblog.data.NewsRepository
import com.example.kotlinblog.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String
    ): NewsRepository

}