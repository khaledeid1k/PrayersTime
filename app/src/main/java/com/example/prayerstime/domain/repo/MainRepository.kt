package com.example.prayerstime.domain.repo

import com.example.prayerstime.domain.model.Pray


interface MainRepository {
    suspend fun getAllTimesPray(
        date: String,
        latitude: Double,
        longitude: Double,
        method: Int
    ): Pray?



    suspend fun getSelectedDay(
        date: String, latitude: Double, longitude: Double, method: Int
    ): Pray

    suspend fun savePray(prayResponseEntity: Pray) : Long

}
