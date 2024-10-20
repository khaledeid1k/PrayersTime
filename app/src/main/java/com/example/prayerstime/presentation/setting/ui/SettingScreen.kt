package com.example.prayerstime.presentation.setting.ui

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.prayerstime.core.theme.PrayersTimeTheme
import com.example.prayerstime.domain.model.Setting
import com.example.prayerstime.presentation.setting.ui.components.AnimatedPreloader
import com.example.prayerstime.presentation.setting.ui.components.MenuSample
import com.example.prayerstime.presentation.setting.ui.components.PermissionLocation
import com.example.prayerstime.presentation.setting.view_model.SettingViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun SettingScreen(navController: NavController) {
    val settingViewModel = hiltViewModel<SettingViewModel>()
    val settingState by settingViewModel.settingState.collectAsState()

    SettingContent(settingEvent = settingViewModel, settingState = settingState, navController)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun SettingContent(settingEvent: SettingEvent, settingState: Setting, navController: NavController) {
    val context = LocalContext.current
    var selectedMethod by remember { mutableStateOf("") }
    var isPermeationGrantedState by remember { mutableStateOf(false) }
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
        ) {

            AnimatedPreloader(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .fillMaxSize(.5f)
            )
            Text("Your Zone", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            PermissionLocation{
                isPermeationGranted,latitude,longitude->
                isPermeationGrantedState=isPermeationGranted
                settingEvent.isPermeationGranted(isPermeationGranted,latitude,longitude)
            }
            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            Text("Your Method", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(selectedMethod.ifEmpty { "Select Your Method" })
                MenuSample { method, value ->
                    selectedMethod = method
                    settingEvent.selectedMethod(value)
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            Button(
                onClick = {
                    if(selectedMethod.isNotBlank()&& isPermeationGrantedState) {
                        settingEvent.navigateToHome(navController)
                    }else{
                        Toast.makeText(context, "Please check you are selected Your Method and Your Location", Toast.LENGTH_SHORT).show()
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                Text("Submit")
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
@Preview(showBackground = true)
fun SettingPreview(modifier: Modifier = Modifier) {
    PrayersTimeTheme {
        SettingContent(object : SettingEvent {
            override fun isPermeationGranted(isGranted:Boolean,latitude:Double,longitude:Double) {

            }

            override fun selectedMethod(method: Int) {

            }

            override fun navigateToHome(navController: NavController) {

            }

        }, Setting() ,
            navController = NavController(LocalContext.current))
    }
}

