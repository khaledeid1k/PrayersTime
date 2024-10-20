package com.example.prayerstime.presentation.setting.ui

import androidx.navigation.NavController

interface SettingEvent {

fun isPermeationGranted(isGranted:Boolean,latitude:Double,longitude:Double)
fun selectedMethod(method:Int)
fun navigateToHome(navController: NavController)
}