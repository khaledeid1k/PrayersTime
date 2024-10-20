package com.example.prayerstime.domain.use_case

import com.example.prayerstime.domain.model.Pray
import com.example.prayerstime.domain.repo.MainRepository
import javax.inject.Inject


class SavePrayersDataUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(pray: Pray) = repository.savePray(pray)


}