package com.example.kotlinblog.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Source (
    val id: String? = null,
    val name: String
)

@Serializable
data class Article(
    val source: Source,
    val author: String? = null,
    val title: String,
    val description: String? = null,
    val url: String,
    @SerialName("urlToImage")
    val imageUrl: String? = null,
    val publishedAt: String,
    val content: String? = null
)