package com.example.news.data


import com.example.news.models.ArticleApiModel

import retrofit2.http.*

interface API {
    @GET("top-headlines")
    suspend fun query(
        @Query("apiKey") apiKey: String = "9e39934e997343cf8a4b6010d533a801",
        @Query("category") category: String = "science",
        @Query("country") ru: String = "ru",
        @Query("q") q: String = "",
    ): ArticleApiModel
}

//https://newsapi.org/v2/top-headlines?category=science&country=ru&apiKey=9e39934e997343cf8a4b6010d533a801&q=