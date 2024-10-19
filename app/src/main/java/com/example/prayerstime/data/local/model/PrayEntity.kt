package com.example.prayerstime.data.local.model

import androidx.room.Entity

@Entity(tableName = "PrayResponse_table",primaryKeys = ["latitude", "longitude", "method", "date"])

data class PrayEntity(
    val asrTime: String?,
    val dhuhrTime: String?,
    val fajrTime: String?,
    val ishaTime: String?,
    val maghribTime: String?,
    val sunriseTime: String?,
    val location: String?,
    val latitude: Double,
    val longitude: Double,
    var method: Int,
    var date: String,
    )