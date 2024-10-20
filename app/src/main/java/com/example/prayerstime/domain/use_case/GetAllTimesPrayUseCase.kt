package com.example.prayerstime.domain.use_case

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.prayerstime.domain.model.Pray
import com.example.prayerstime.domain.repo.MainRepository
import com.example.prayerstime.utils.dateDay
import javax.inject.Inject


class GetAllTimesPrayUseCase @Inject constructor(
    private val repository: MainRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
        method: Int,
        date: String = dateDay(),
    ): Pray? {
        return repository.getAllTimesPray(date, latitude, longitude, method)

    }
}