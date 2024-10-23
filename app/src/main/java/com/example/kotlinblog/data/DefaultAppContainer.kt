package com.example.kotlinblog.data

import com.example.kotlinblog.BuildConfig
import com.example.kotlinblog.network.NewsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val newsRepository: NewsRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://newsapi.org/v2/"

    private val API_KEY = BuildConfig.API_KEY

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
        encodeDefaults = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val apiService: NewsApiService = retrofit.create(NewsApiService::class.java)

    override val newsRepository = NetworkNewsRepository(apiService)
}