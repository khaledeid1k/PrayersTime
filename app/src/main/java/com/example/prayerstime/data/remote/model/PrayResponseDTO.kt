package com.example.prayerstime.data.remote.model


import com.google.gson.annotations.SerializedName

data class PrayResponseDTO(
    @SerializedName("date")
    val date: Date = Date(),
    @SerializedName("timings")
    val timings: Timings = Timings(),
    @SerializedName("meta")
    val meta: Meta = Meta(),
)

data class Date(
    @SerializedName("readable")
    val readable: String = ""
)

data class Timings(
    @SerializedName("Asr")
    val asr: String = "",
    @SerializedName("Dhuhr")
    val dhuhr: String = "",
    @SerializedName("Fajr")
    val fajr: String = "",
    @SerializedName("Isha")
    val isha: String = "",
    @SerializedName("Maghrib")
    val maghrib: String = "",
    @SerializedName("Sunrise")
    val sunrise: String = "",

    )