package com.example.prayerstime.di

import com.airbnb.lottie.BuildConfig
import com.example.prayerstime.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Provides
    @Singleton
     fun provideOkHttpClient() =
        OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
         fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://api.aladhan.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

       return provideRetrofit().create(ApiService::class.java)
    }



}