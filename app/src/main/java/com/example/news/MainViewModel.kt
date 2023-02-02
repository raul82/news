package com.example.divtech

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.GlobalState
import com.example.news.data.ArticlesRepository
import com.example.news.models.Article
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

sealed class LoadingState {
    object Default : LoadingState()
    object Start : LoadingState()
    data class Stop(val errorMsg: String? = null) : LoadingState()
}


class MainViewModel(private val articlesRepository: ArticlesRepository) :
    ViewModel() , KoinComponent {

    val router         : Router by inject()
    val navigatorHolder: NavigatorHolder by inject()

    var currentStateLiveDate =

        MutableLiveData<GlobalState>().apply { value = GlobalState.REGISTRATION }

    var loadingStateLiveDate =
        MutableLiveData<LoadingState>().apply { value = LoadingState.Default }


    var counter = 0
    var counterForChannel = 0
    val data : Flow<Int> = flow {
        while (true) {
            emit(counter++)
            delay(3000)
        }
    }

    val channel = Channel<Int>()

    private var articles : List<Article>? = null
    init {
        viewModelScope.launch(Dispatchers.IO) {
            articles =   articlesRepository.getArticles()
        }

        router.exit()
    }

    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        loadingStateLiveDate.postValue(LoadingState.Stop(exception.toString()))
    }

}