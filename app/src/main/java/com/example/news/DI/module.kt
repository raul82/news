package com.example.news.DI

import com.example.divtech.MainViewModel
import com.example.news.data.ArticlesRepository
import com.example.news.data.ArticlesRepositoryImpl
import com.example.news.data.NetworkDataSource
import com.example.news.data.RetrofitDataSource
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

    singleOf(::RetrofitDataSource) { bind<NetworkDataSource>()}
    singleOf(::ArticlesRepositoryImpl) { bind<ArticlesRepository>()}
    viewModelOf(::MainViewModel)
}

