package com.example.prayerstime.domain.model

sealed class PrayerTime(val prayName: String) {
    object Fajr : PrayerTime("Fajr")
    object Sunrise : PrayerTime("Sunrise")
    object Dhuhr : PrayerTime("Dhuhr")
    object Asr : PrayerTime("Asr")
    object Maghrib : PrayerTime("Maghrib")
    object Isha : PrayerTime("Isha")


}