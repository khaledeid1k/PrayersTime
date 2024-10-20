package com.example.prayerstime.presentation.home.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.prayerstime.R
import com.example.prayerstime.domain.model.Pray
import com.example.prayerstime.presentation.home.ui.components.HeaderBody
import com.example.prayerstime.presentation.home.ui.components.HomeHeader
import com.example.prayerstime.presentation.home.ui.components.PrayItem
import com.example.prayerstime.presentation.home.view_model.HomeViewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.minutes

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen() {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val homeState by homeViewModel.homeState.collectAsState()

    HomeContent(homeState = homeState, homeEvents = homeViewModel)

}

@Composable
fun HomeContent(homeState: Pray, homeEvents: HomeEvents) {
    var remainTimeMinute by remember { mutableIntStateOf(homeState.remainTimeMinute) }
    var remainTimeHour by remember { mutableIntStateOf(homeState.remainTimeHour) }
    LaunchedEffect(homeState.remainTimeMinute, homeState.remainTimeHour) {
        remainTimeMinute = homeState.remainTimeMinute
        remainTimeHour = homeState.remainTimeHour
    }
    LaunchedEffect(Unit) {
        while (true) {
            if(remainTimeMinute==0&&remainTimeHour==0){
                homeEvents.updateLeftTime()
            }
            delay(1.minutes)
            if (remainTimeMinute > 0) {
                remainTimeMinute--
            } else if (remainTimeHour > 0) {
                remainTimeHour--
                remainTimeMinute = 59
            }
            if(remainTimeMinute==0&&remainTimeHour==0){
                homeEvents.updateLeftTime()
            }
        }

    }
    Scaffold { padding ->
            Column (
                Modifier
                    .padding(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                HomeHeader(date = homeState.date, location =homeState.location,
                    nextPray ={homeEvents.nextPray()} , previousPray ={ homeEvents.previousPray()} )
                HorizontalDivider(Modifier.padding(8.dp), thickness = 2.dp, color = Color.Black)
                HeaderBody(nextPray =homeState.nextPray ,
                    remainTimeHour =remainTimeHour ,
                    remainTimeMinute =remainTimeMinute
                )
                HorizontalDivider(Modifier.padding(8.dp), thickness = 2.dp, color = Color.Black)
                LazyColumn {
                    items(homeState.prayItems){
                        PrayItem(prayName = it.prayName, prayTime = it.prayTime)
                    }
                }
                HorizontalDivider(
                    Modifier.padding(vertical = 16.dp),
                    thickness = 2.dp,
                    color = Color.Black
                )

                Button(onClick = {homeEvents.showQibla()}) {
                    Text(stringResource(R.string.show_qibla_direction_on_map))
                }
        }
    }
}



@Composable
@Preview(showBackground = true)
fun HomePreview() {
    HomeContent(homeState = Pray(), homeEvents = object : HomeEvents {
        override fun showQibla() {
        }

        override fun nextPray() {

        }

        override fun previousPray() {
        }

        override fun updateLeftTime() {

        }
    })

}
