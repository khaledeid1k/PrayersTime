package com.example.prayerstime.domain.use_case

import com.example.prayerstime.domain.repo.MainRepository
import javax.inject.Inject


class GetSavedPrayersUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(  date: String, latitude: Double, longitude: Double, method: Int) = repository.getSavedDay(
        date, latitude, longitude, method
    )


}