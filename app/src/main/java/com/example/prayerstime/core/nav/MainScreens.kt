package com.example.prayerstime.core.nav

import kotlinx.serialization.Serializable

sealed class MainScreens {


    @Serializable
    data class Home(val latitude:Double,val longitude:Double, val method:Int)

    @Serializable
    object  Setting

}
