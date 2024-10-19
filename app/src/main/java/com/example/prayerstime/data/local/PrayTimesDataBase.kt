package com.example.prayerstime.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.prayerstime.data.local.model.PrayEntity


@Database(entities = [PrayEntity::class], version = 1)
abstract class PrayTimesDataBase:RoomDatabase() {
    abstract fun prayTimeDao(): PrayResponseDao
}
