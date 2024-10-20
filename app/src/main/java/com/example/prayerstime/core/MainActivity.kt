package com.example.prayerstime.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.prayerstime.core.nav.MainScreens
import com.example.prayerstime.core.nav.createHomeNavGraph
import com.example.prayerstime.core.theme.PrayersTimeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrayersTimeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    MainGraph()
                }
            }
        }
    }

    @Composable
    fun MainGraph() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = MainScreens.Setting,
            modifier = Modifier.fillMaxSize()
        ) {
            createHomeNavGraph(
                navController = navController,
            )
        }
}
}


