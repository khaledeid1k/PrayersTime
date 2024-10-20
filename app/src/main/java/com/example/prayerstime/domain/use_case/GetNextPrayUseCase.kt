package com.example.prayerstime.domain.use_case

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.prayerstime.domain.model.PrayInfo
import com.example.prayerstime.utils.convertSecondsToHoursAndMinutes
import com.example.prayerstime.utils.extractHourAndMinute
import com.example.prayerstime.utils.getDateComponents
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class GetNextPrayUseCase @Inject constructor(){
    @RequiresApi(Build.VERSION_CODES.O)
     operator fun invoke(prayers: List<PrayInfo>, date :String): Triple<String?, Int, Int> {
        var nearestPrayTime: Long = Long.MAX_VALUE
        var nearestPrayName: String? = null
        val now = LocalDateTime.now()
        val (day, month, year) = getDateComponents(date)
        for (pray in prayers) {
            val extractHourAndMinute = extractHourAndMinute(pray.prayTime)
            val specificTime = LocalDateTime.of(
                year,
                month,
                day,
                extractHourAndMinute.first,
                extractHourAndMinute.second
            )
            when {
                specificTime.isAfter(now) -> {
                    val differenceInSeconds =
                        specificTime.toEpochSecond(ZoneOffset.UTC) - now.toEpochSecond(ZoneOffset.UTC)
                    if (differenceInSeconds in 1..nearestPrayTime) {
                        nearestPrayTime = differenceInSeconds
                        nearestPrayName = pray.prayName
                    }
                }
            }
        }
        val (hours, minutes) = if (nearestPrayTime != Long.MAX_VALUE) convertSecondsToHoursAndMinutes(nearestPrayTime) else Pair(0, 0)
        return Triple(nearestPrayName, hours, minutes)

    }
}
