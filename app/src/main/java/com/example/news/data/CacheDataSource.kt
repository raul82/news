package com.example.news.data

import com.example.news.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow

class CacheDataSource : LocalDataSource {
    private var articlesCache: List<Article> = listOf()

    override suspend fun getArticles(): Flow<List<Article>>{
      return  flow {
          emit(articlesCache)
      }
    }

    override suspend fun saveArticles(cache: List<Article>) {
        articlesCache = cache
    }
}