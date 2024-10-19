package com.example.prayerstime.domain.model

sealed class PrayerTime(val prayName: String, val prayTimeAmOrPm: String) {
    object Fajr : PrayerTime("Fajr", "Am")
    object Sunrise : PrayerTime("Sunrise", "Am")
    object Dhuhr : PrayerTime("Dhuhr", "Pm")
    object Asr : PrayerTime("Asr", "Pm")
    object Maghrib : PrayerTime("Maghrib", "Pm")
    object Isha : PrayerTime("Isha", "Pm")


}