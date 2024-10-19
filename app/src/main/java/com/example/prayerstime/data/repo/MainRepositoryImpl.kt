package com.example.prayerstime.data.repo

import com.example.prayerstime.data.local.PrayResponseDao
import com.example.prayerstime.data.local.model.PrayEntity
import com.example.prayerstime.data.remote.ApiService
import com.example.prayerstime.data.remote.utils.safeCallApi
import com.example.prayerstime.data.mapper.toPray
import com.example.prayerstime.data.mapper.toPrayEntity
import com.example.prayerstime.domain.model.Pray
import com.example.prayerstime.domain.repo.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService : ApiService,
    private val prayTimeDao : PrayResponseDao,

    ): MainRepository {
   override suspend fun getAllTimesPray(
        date: String , latitude: Double, longitude: Double, method: Int
    ): Pray? {
        return safeCallApi {
            apiService.getTimesPray(
                date, latitude,
                longitude, method
            )
        }?.toPray()
    }


   override suspend fun savePray(pray: Pray) =   prayTimeDao.insert(pray.toPrayEntity())

    override suspend fun getSelectedDay(
        date: String , latitude: Double, longitude: Double, method: Int
    ) :Pray { return prayTimeDao.getAllPrayTimes(date,latitude,longitude,method).toPray() }





}
