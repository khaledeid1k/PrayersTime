package com.example.prayerstime.domain.model


data class Pray(
    val date: String = "N/A",
    val location: String = "Unknown Location",
    val prayItems: List<PrayInfo> = emptyList(),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    var method: Int = 0,
    val remainTimeHour: Int = 0,
    val remainTimeMinute: Int = 1,
    val nextPray: String = "None"
)
data class PrayInfo(
    val prayName : String,
    val prayTime : String,
)