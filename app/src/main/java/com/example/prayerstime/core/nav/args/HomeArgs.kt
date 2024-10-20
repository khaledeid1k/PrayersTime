package com.example.prayerstime.core.nav.args

import androidx.lifecycle.SavedStateHandle

class HomeArgs(savedStateHandle: SavedStateHandle) {
    val latitude: Double = savedStateHandle[HOME_SCREEN_LATITUDE] ?: 0.0
    val longitude: Double = savedStateHandle[HOME_SCREEN_LONGITUDE] ?: 0.0
    val method: Int = savedStateHandle[HOME_SCREEN_METHOD] ?: 0


    companion object {
        const val HOME_SCREEN_LATITUDE = "latitude"
        const val HOME_SCREEN_LONGITUDE = "longitude"
        const val HOME_SCREEN_METHOD = "method"


    }

}