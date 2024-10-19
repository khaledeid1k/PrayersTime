package com.example.prayerstime.data.mapper

import com.example.prayerstime.data.local.model.PrayEntity
import com.example.prayerstime.data.remote.model.PrayResponseDTO
import com.example.prayerstime.domain.model.Pray
import com.example.prayerstime.domain.model.PrayInfo
import com.example.prayerstime.domain.model.PrayerTime

fun PrayResponseDTO.toPray(): Pray {
   return Pray(
        date = date.readable ,
        location = meta.timezone,
       latitude=meta.latitude?:0.0,
       longitude=meta.longitude?:0.0,
       method=meta.method.id,
        prayItems = listOf(
            PrayInfo(
                prayName = PrayerTime.Fajr.prayName,
                prayTime = timings.fajr,
                prayTimeAmOrPm = PrayerTime.Fajr.prayTimeAmOrPm
            ),
            PrayInfo(
                prayName = PrayerTime.Sunrise.prayName,
                prayTime = timings.sunrise,
                prayTimeAmOrPm = PrayerTime.Sunrise.prayTimeAmOrPm
            ),
            PrayInfo(
                prayName = PrayerTime.Dhuhr.prayName,
                prayTime = timings.dhuhr,
                prayTimeAmOrPm = PrayerTime.Dhuhr.prayTimeAmOrPm
            ),
            PrayInfo(
                prayName = PrayerTime.Asr.prayName,
                prayTime = timings.asr,
                prayTimeAmOrPm = PrayerTime.Asr.prayTimeAmOrPm
            ),
            PrayInfo(
                prayName = PrayerTime.Maghrib.prayName,
                prayTime = timings.maghrib,
                prayTimeAmOrPm = PrayerTime.Maghrib.prayTimeAmOrPm
            ),
            PrayInfo(
                prayName = PrayerTime.Isha.prayName,
                prayTime = timings.isha,
                prayTimeAmOrPm = PrayerTime.Isha.prayTimeAmOrPm
            ),



        ) ,
    )
}

fun Pray.toPrayEntity(): PrayEntity{
    return  PrayEntity(
        asrTime = prayItems.find { it.prayName == PrayerTime.Asr.prayName }?.prayTime,
        dhuhrTime = prayItems.find { it.prayName == PrayerTime.Dhuhr.prayName }?.prayTime,
        fajrTime = prayItems.find { it.prayName == PrayerTime.Fajr.prayName }?.prayTime,
        ishaTime = prayItems.find { it.prayName == PrayerTime.Isha.prayName }?.prayTime,
        maghribTime = prayItems.find { it.prayName == PrayerTime.Maghrib.prayName }?.prayTime,
        sunriseTime = prayItems.find { it.prayName == PrayerTime.Sunrise.prayName }?.prayTime,
        latitude = latitude,
        longitude = longitude,
        method = method,
        date = date,
        location = location,


    )
}

fun PrayEntity.toPray(): Pray{
    return Pray(
        prayItems= listOf(
            PrayInfo(
                prayName = PrayerTime.Fajr.prayName,
                prayTime = fajrTime?:"",
                prayTimeAmOrPm = PrayerTime.Fajr.prayTimeAmOrPm
            ),
            PrayInfo(
                prayName = PrayerTime.Sunrise.prayName,
                prayTime = sunriseTime?:"",
                prayTimeAmOrPm = PrayerTime.Sunrise.prayTimeAmOrPm
            ),
            PrayInfo(
                prayName = PrayerTime.Dhuhr.prayName,
                prayTime = dhuhrTime?:"",
                prayTimeAmOrPm = PrayerTime.Dhuhr.prayTimeAmOrPm
            ),
            PrayInfo(
                prayName = PrayerTime.Asr.prayName,
                prayTime = asrTime?:"",
                prayTimeAmOrPm = PrayerTime.Asr.prayTimeAmOrPm
            ),
            PrayInfo(
                prayName = PrayerTime.Maghrib.prayName,
                prayTime = maghribTime?:"",
                prayTimeAmOrPm = PrayerTime.Maghrib.prayTimeAmOrPm
            ),
            PrayInfo(
                prayName = PrayerTime.Isha.prayName,
                prayTime = ishaTime?:"",
                prayTimeAmOrPm = PrayerTime.Isha.prayTimeAmOrPm
            ),

        ) ,
        latitude = latitude,
        longitude = longitude,
        method = method,
        date = date,
        location = location?:"",
    )
}