package com.example.prayerstime.presentation.setting

interface SettingEvent {

fun isPermeationGranted(isGranted:Boolean,latitude:Double,longitude:Double)
fun selectedMethod(method:Int)
fun navigateToHome()
}