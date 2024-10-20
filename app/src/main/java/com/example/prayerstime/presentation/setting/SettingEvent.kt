package com.example.prayerstime.presentation.setting

import androidx.navigation.NavController

interface SettingEvent {

fun isPermeationGranted(isGranted:Boolean,latitude:Double,longitude:Double)
fun selectedMethod(method:Int)
fun navigateToHome(navController: NavController)
}