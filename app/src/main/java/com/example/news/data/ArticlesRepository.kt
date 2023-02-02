package com.example.news.data

import com.example.news.models.Article

interface ArticlesRepository {
    suspend fun getArticles() : List<Article>
    suspend fun getArticleByTitle() : Article
}