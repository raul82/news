package com.example.news.data

import com.example.news.models.Article
import kotlinx.coroutines.flow.Flow


class ArticlesRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) : ArticlesRepository {
    override suspend fun getArticles(fromCache: Boolean): Flow<List<Article>> {
        if (!fromCache)
            localDataSource.saveArticles(networkDataSource.getArticles())

        return localDataSource.getArticles()
    }

    override suspend fun getArticleByTitle(): Article {
        TODO("Not yet implemented")
    }


}