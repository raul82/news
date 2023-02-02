package com.example.news.data

import com.example.news.models.Article

interface NetworkDataSource{
    suspend fun getArticles (): List<Article>

}