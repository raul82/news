package com.example.news.data

import com.example.news.models.Article
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    suspend fun getArticles(fromCache : Boolean = false) : Flow<List<Article>>
    suspend fun getArticleByTitle() : Article
}