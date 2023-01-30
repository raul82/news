package com.example.news.DI

import com.example.news.network.NetworkDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val appModule = module {
    singleOf(::RetrofitDataSource) { bind<NetworkDataSource>()}
}