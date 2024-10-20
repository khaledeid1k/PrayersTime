package com.example.prayerstime.core.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.prayerstime.core.nav.args.HomeArgs.Companion.HOME_SCREEN_LATITUDE
import com.example.prayerstime.core.nav.args.HomeArgs.Companion.HOME_SCREEN_LONGITUDE
import com.example.prayerstime.core.nav.args.HomeArgs.Companion.HOME_SCREEN_METHOD
import com.example.prayerstime.presentation.home.ui.HomeScreen
import com.example.prayerstime.presentation.setting.ui.SettingScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.createHomeNavGraph(
    navController: NavController
) {

    composable<MainScreens.Setting > {

        SettingScreen(navController) }

    composable<MainScreens.Home> {
        val args = it.toRoute<MainScreens.Home>()
        it.savedStateHandle[HOME_SCREEN_LATITUDE] = args.latitude
        it.savedStateHandle[HOME_SCREEN_LONGITUDE] = args.longitude
        it.savedStateHandle[HOME_SCREEN_METHOD] = args.method
        HomeScreen(navController) }




}
fun NavController.navigateToHomeScreen(latitude:Double,longitude:Double, method:Int) {
    navigate(MainScreens.Home(latitude,longitude,method))
}

