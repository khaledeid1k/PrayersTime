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
            ),
            PrayInfo(
                prayName = PrayerTime.Sunrise.prayName,
                prayTime = timings.sunrise,
            ),
            PrayInfo(
                prayName = PrayerTime.Dhuhr.prayName,
                prayTime = timings.dhuhr,
            ),
            PrayInfo(
                prayName = PrayerTime.Asr.prayName,
                prayTime = timings.asr,
            ),
            PrayInfo(
                prayName = PrayerTime.Maghrib.prayName,
                prayTime = timings.maghrib,
            ),
            PrayInfo(
                prayName = PrayerTime.Isha.prayName,
                prayTime = timings.isha,
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
            ),
            PrayInfo(
                prayName = PrayerTime.Sunrise.prayName,
                prayTime = sunriseTime?:"",
            ),
            PrayInfo(
                prayName = PrayerTime.Dhuhr.prayName,
                prayTime = dhuhrTime?:"",
            ),
            PrayInfo(
                prayName = PrayerTime.Asr.prayName,
                prayTime = asrTime?:"",
            ),
            PrayInfo(
                prayName = PrayerTime.Maghrib.prayName,
                prayTime = maghribTime?:"",
            ),
            PrayInfo(
                prayName = PrayerTime.Isha.prayName,
                prayTime = ishaTime?:"",
            ),

        ) ,
        latitude = latitude,
        longitude = longitude,
        method = method,
        date = date,
        location = location?:"",
    )
}