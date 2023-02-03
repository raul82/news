package com.example.news.DI

import com.example.news.MainViewModel
import com.example.news.data.*
import kotlinx.coroutines.Dispatchers

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dispatchersKoinModule = module{
    single{ Dispatchers.IO}
}


val netWorkModule = module(createdAtStart=true){
    includes(dispatchersKoinModule)

    singleOf(::CacheDataSource)  {bind<LocalDataSource>()}
    singleOf(::RetrofitDataSource) { bind<NetworkDataSource>()}
    singleOf(::ArticlesRepositoryImpl) { bind<ArticlesRepository>()}
    viewModelOf(::MainViewModel)
}


