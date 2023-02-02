package com.example.news.DI

import com.example.news.data.ArticlesRepository
import com.example.news.data.NetworkDataSource
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val navigationModule = module(createdAtStart=true) {
     val cicerone : Cicerone<Router> = Cicerone.create()

    single{cicerone.router}
    single{cicerone.getNavigatorHolder()}

}
