package com.example.prayerstime.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun dateDay(): String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
fun getSelectedDate(count: Long): String = LocalDate.now().plusDays(count).format( DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault()))
fun String.convertDate(): String {
    val inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val date = inputFormat.parse(this)
    return outputFormat.format(date)
}
fun getDateComponents(dateString: String): Triple<Int, Int, Int> {
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
    val date = dateFormat.parse(dateString)

    val calendar = Calendar.getInstance()
    calendar.time = date

    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH) + 1
    val year = calendar.get(Calendar.YEAR)

    return Triple(day, month, year)
}
fun extractHourAndMinute(timeString: String): Pair<Int, Int> {
    val (hour, minute) = timeString.split(":").map { it.toInt() }
    return Pair(hour, minute)
}

fun convertSecondsToHoursAndMinutes(seconds: Long): Pair<Int, Int> {
    val hours = (seconds / 3600).toInt()
    val minutes = ((seconds % 3600) / 60).toInt()
    return Pair(hours, minutes)
}
fun String.convertTo12HourFormat(): String {
    val inputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())

    val date = inputFormat.parse(this)

    return outputFormat.format(date!!)
}