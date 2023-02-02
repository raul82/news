package com.example.news

import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun MainFragment() = FragmentScreen {
        MainFragment.getNewInstance()
    }

}