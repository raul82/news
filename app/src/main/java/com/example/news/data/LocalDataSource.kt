package com.example.news.data

import com.example.news.models.Article
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getArticles() : Flow<List<Article>>
    suspend fun saveArticles(cache : List<Article>)

}