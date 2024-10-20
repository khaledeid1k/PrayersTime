package com.example.prayerstime.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.prayerstime.data.local.model.PrayEntity

@Dao
interface PrayResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insert(prayEntity: PrayEntity) : Long
    @Query("SELECT * FROM PrayResponse_table  WHERE date " +
            "==:data and latitude==:latitude and longitude==:longitude and method==:method")
     suspend fun getAllPrayTimes(data: String, latitude: Double, longitude: Double, method: Int
    ):PrayEntity?
}