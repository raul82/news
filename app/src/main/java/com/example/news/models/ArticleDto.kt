package com.example.news.models

data class ArticleDto(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)