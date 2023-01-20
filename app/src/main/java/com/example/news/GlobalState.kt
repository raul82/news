package com.example.news

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize

enum class GlobalState( val state : String) : Parcelable {
  REGISTRATION("REGISTERED"),
  AUTHORIZATION("AUTHORIZATION"),
  SELECT_DESTINATION("SELECT_DESTINATION"),
  REQUEST_TICKET("REQUEST_TICKET"),
  ALL_TICKETS("ALL_TICKETS")
}
