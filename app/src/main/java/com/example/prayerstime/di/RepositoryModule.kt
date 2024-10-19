package com.example.prayerstime.di

import com.example.prayerstime.data.local.PrayResponseDao
import com.example.prayerstime.data.remote.ApiService
import com.example.prayerstime.data.repo.MainRepositoryImpl
import com.example.prayerstime.domain.repo.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun providesRepository(
        apiService: ApiService, prayTimeDao : PrayResponseDao
    ) : MainRepository {
        return  MainRepositoryImpl( apiService,prayTimeDao)
    }
}



