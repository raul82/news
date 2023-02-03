package com.example.news.models

data class ArticleApiModel(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)