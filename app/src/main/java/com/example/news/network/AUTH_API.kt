package com.example.divtech.network


import com.example.news.BuildConfig

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
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


    @POST("api/auth/get-token")
    suspend fun auth(@Body request : Map<String, String> ): Map<String, String>






/*    @POST("api/search-trains")
    suspend fun getTrains( @Header("Authorization")  token: String , @Body request : TrainRequest): TrainsDto

    @POST("api/search-trains")
    suspend fun getTicket( @Header("Authorization")  token: String , @Body request : TicketRequest): Map<String, Any>*/
}




private fun interceptor(): Interceptor {
    return Interceptor { chain: Interceptor.Chain ->
        val request = chain.request()
            .newBuilder()
            .addHeader("X-Requested-With", "XMLHttpRequest")
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