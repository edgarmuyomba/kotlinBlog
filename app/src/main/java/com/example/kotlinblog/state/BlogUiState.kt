package com.example.kotlinblog.state

import com.example.kotlinblog.models.Article

sealed interface BlogUiState {
    object Loading : BlogUiState
    data class Success(val posts: List<Article>) : BlogUiState
    data class Error(val message: String) : BlogUiState
}