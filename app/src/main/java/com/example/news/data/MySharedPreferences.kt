package com.example.news.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

object MySharedPreferences {
    lateinit var preferences: SharedPreferences
    val gson = Gson()

    fun init(context: Context): MySharedPreferences {
        preferences =
            context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        return this
    }


    fun setPhone(phone: String?) {
        val editor = preferences.edit()
        editor.putString("phone", phone)
        editor.apply()
    }

    fun getPhone(): String? {
        return preferences.getString("phone", null)
    }


    var token: String?
        get() =  preferences.getString("token", null)
        set(value) {
            preferences.edit().putString("token", value).apply()
        }


/*    fun addTicket(ticket : TicketRequest) {
        val array : ArrayList<TicketRequest> = getTickets()

        array.add(ticket)
        val prefsEditor = preferences.edit()

        val json = gson.toJson(array)
        prefsEditor.putString("tickets", json)
        prefsEditor.apply()
    }

    fun getTickets(): ArrayList<TicketRequest> {
      val arayList = ArrayList<TicketRequest>()
        val type = object : TypeToken<List<TicketRequest>>() {}.type

        val json =
            preferences.getString("tickets", null)
        json?.let {
            val array = gson.fromJson<List<TicketRequest>>(json,type)
            return  array as ArrayList<TicketRequest>
        } ?: run {
            return  arayList
        }
    }*/

}