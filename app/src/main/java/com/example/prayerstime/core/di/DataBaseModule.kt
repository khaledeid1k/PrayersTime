package com.example.prayerstime.core.di

import android.content.Context
import androidx.room.Room
import com.example.prayerstime.data.local.PrayTimesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
     fun buildDataBase(@ApplicationContext context: Context): PrayTimesDataBase {
        return Room.databaseBuilder(context, PrayTimesDataBase::class.java,"PrayTimesDataBase").build()
    }

    @Provides
    @Singleton
    fun prayResponseDao(prayTimesDataBase:PrayTimesDataBase)=prayTimesDataBase.prayTimeDao()
}