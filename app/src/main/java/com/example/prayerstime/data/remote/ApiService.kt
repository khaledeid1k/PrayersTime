package com.example.prayerstime.data.remote

import com.example.prayerstime.data.remote.model.PrayResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("timings/{date}")
    suspend fun getTimesPray(
        @Path("date") date: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("method") method: Int
    ): BasicApiResponse<PrayResponseDTO>


}