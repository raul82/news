package com.example.divtech

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.divtech.models.*
import com.example.divtech.network.AUTH_API
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
import retrofit2.HttpException

sealed class LoadingState {
    object Default : LoadingState()
    object Start : LoadingState()
    data class Stop(val errorMsg: String? = null) : LoadingState()
}


class MainViewModel(private val pref: MySharedPreferences, private val authApi: AUTH_API) :
    ViewModel() {

    var currentStateLiveDate =

        MutableLiveData<GlobalState>().apply { value = GlobalState.REGISTRATION }

    var loadingStateLiveDate =
        MutableLiveData<LoadingState>().apply { value = LoadingState.Default }

//    var allTicketsLiveData =
//        MutableLiveData<List<TicketRequest>>()

/*    val cityList = listOf(
        City("AAA"),
        City("BBB"),
        City("CCC"),
        City("DDD"),
    )*/

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
        if (isUserRegistered())
            goToState(GlobalState.SELECT_DESTINATION)


        viewModelScope.launch(Dispatchers.IO) {
                while (true) {
                    channel.send(counterForChannel++)
                    delay(3000)
                }
        }


    }

    var phone: String? = null
    val token: String get() = "Bearer ${pref.token}" ?: ""

    var firstName: String? = null
    var lastName: String? = null

    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        loadingStateLiveDate.postValue(LoadingState.Stop(exception.toString()))
    }

    private fun isUserRegistered(): Boolean {
        return pref.getPhone()?.let {
            true
        } ?: false
    }

    fun userRegistration() {
        viewModelScope.launch(exceptionHandler) {
            loadingStateLiveDate.postValue(LoadingState.Start)
            phone?.let {
                pref.setPhone(phone)
                auth()
                goToState(GlobalState.SELECT_DESTINATION)
            }
            loadingStateLiveDate.postValue(LoadingState.Stop())
        }
    }

    private fun goToState(newState: GlobalState) {
        currentStateLiveDate.value = newState
    }


    fun trainRequest() {
        viewModelScope.launch(exceptionHandler) {
            loadingStateLiveDate.postValue(LoadingState.Start)

//            val dto = authApi.getTrains(token, TrainRequest(cityFrom?.name, cityTo?.name, depDate))
//            trains = dto.trains

            loadingStateLiveDate.postValue(LoadingState.Stop())
            goToState(GlobalState.REQUEST_TICKET)
        }
    }

    fun requestTickets() {
        viewModelScope.launch(exceptionHandler) {
            loadingStateLiveDate.postValue(LoadingState.Start)

/*            authApi.getTicket(token, ticket)
            pref.addTicket(ticket)*/
            loadingStateLiveDate.postValue(LoadingState.Stop())


            getAllTicketRequest()
        }
    }

    fun getAllTicketRequest() {
        viewModelScope.launch(exceptionHandler) {
            loadingStateLiveDate.postValue(LoadingState.Start)
//            val tickets = pref.getTickets()
//            allTicketsLiveData.postValue(tickets)
            loadingStateLiveDate.postValue(LoadingState.Stop())
            goToState(GlobalState.ALL_TICKETS)
        }
    }


    private suspend fun auth() {
/*        val answer = authApi.auth(
 *//*           mapOf(
                "app_key" to APP_KEY,
                "app_secret" to APP_SECRET
            )*//*
        )
        pref.token = answer["token"]
        */
    }

}