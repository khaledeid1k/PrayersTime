package com.example.prayerstime.presentation.home.ui

interface HomeEvents{
    fun showQibla()
    fun getSelectedPrayersDate(count: Long)
    fun updateLeftTime()
    fun getAllPray()
}