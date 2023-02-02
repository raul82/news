package com.example.news.data

import com.example.news.models.Article


class ArticlesRepositoryImpl(val networkDataSource: NetworkDataSource) : ArticlesRepository {
    override suspend fun getArticles(): List<Article> {
        return networkDataSource.getArticles()
    }

    override suspend fun getArticleByTitle(): Article {
        TODO("Not yet implemented")
    }


}