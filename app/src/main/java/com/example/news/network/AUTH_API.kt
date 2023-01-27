package com.example.divtech.network


import com.example.news.BuildConfig
import com.example.news.models.ArticleDto
import okhttp3.HttpUrl

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface AUTH_API {
    companion object {
        @JvmStatic
        fun newInstance(): AUTH_API {
            val retrofit = Retrofit.Builder()

                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(provideOkHttpClient())
                .build()

            return retrofit.create(AUTH_API::class.java)
        }
    }


    @GET("top-headlines")
    suspend fun query(
        @Query("apiKey") apiKey: String = "9e39934e997343cf8a4b6010d533a801",
        @Query("category") category : String ="science",
        @Query("q") q: String = "",
        ): ArticleDto

}

// https://newsapi.org/v2/top-headlines?category=science&country=ru&apiKey=9e39934e997343cf8a4b6010d533a801&q=

private fun interceptor(): Interceptor {
    return Interceptor { chain: Interceptor.Chain ->
        val request = chain.request()
        val url = request.url.newBuilder()
//            .addQueryParameter("category", "science")
            .addQueryParameter("country", "ru")
            .addQueryParameter("apiKey","9e39934e997343cf8a4b6010d533a801")
            .build();

        request
            .newBuilder()
            .addHeader("X-Requested-With", "XMLHttpRequest")

            .url(url)
            .build()

        chain.proceed(request)
    }
}

fun provideOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .writeTimeout(
            30,
            TimeUnit.SECONDS
        )
        .readTimeout(
            30,
            TimeUnit.SECONDS
        )
        .connectTimeout(
            30,
            TimeUnit.SECONDS
        )

    builder.addInterceptor(
        interceptor()
    )


    if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        builder.addNetworkInterceptor(httpLoggingInterceptor)
    }


    return builder.build()
}