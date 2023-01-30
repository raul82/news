package com.example.divtech

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.divtech.network.API
import com.example.news.GlobalState
import com.example.news.MySharedPreferences
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class LoadingState {
    object Default : LoadingState()
    object Start : LoadingState()
    data class Stop(val errorMsg: String? = null) : LoadingState()
}


class MainViewModel( val pref: MySharedPreferences,  val authApi: API) :
    ViewModel() {

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
    val channelAsFlow = channel.receiveAsFlow()


    init {


        viewModelScope.launch(Dispatchers.IO) {
             authApi.query()

                while (true) {
                    channel.send(counterForChannel++)
                    delay(3000)
                }
        }

    val list = mutableListOf(1,2,3)

        list.reduce()
    }

    var phone: String? = null


    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        loadingStateLiveDate.postValue(LoadingState.Stop(exception.toString()))
    }

    private fun isUserRegistered(): Boolean {
        return pref.getPhone()?.let {
            true
        } ?: false
    }


}