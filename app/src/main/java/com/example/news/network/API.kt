package com.example.divtech.network


import com.example.news.BuildConfig
import com.example.news.models.ArticleDto

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface API {
    @GET("top-headlines")
    suspend fun query(
        @Query("apiKey") apiKey: String = "9e39934e997343cf8a4b6010d533a801",
        @Query("category") category: String = "science",
        @Query("q") q: String = "",
    ): ArticleDto

}

