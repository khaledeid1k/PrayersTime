package com.example.prayerstime.presentation.setting


import android.Manifest
import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.prayerstime.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.gms.location.LocationServices
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionLocation(permeationGranted:(Boolean,Double,Double)->Unit) {
    val locationPermission = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION
    )
    var locationState by remember { mutableStateOf("") }
    val  fusedLocationClient = LocationServices.getFusedLocationProviderClient(LocalContext.current)

    val context = LocalContext.current
    val showRationalDialog = remember { mutableStateOf(false) }
    if (showRationalDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showRationalDialog.value = false
            },
            title = {
                Text(
                    text = "Permission",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            text = {
                Text(
                    "The Location Access is important for this app. Please grant the permission.",
                    fontSize = 16.sp
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showRationalDialog.value = false
                        val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", context.packageName, null)
                        )
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(context, intent, null)

                    }) {
                    Text("OK", style = TextStyle(color = Color.Black))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showRationalDialog.value = false
                    }) {
                    Text("Cancel", style = TextStyle(color = Color.Black))
                }
            },
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(locationState.ifEmpty { "Select Your Location" })
        Icon(
            painter = painterResource(id = R.drawable.location),
            contentDescription = null,
            modifier = Modifier.clickable {
                if (!locationPermission.status.isGranted) {
                    if (locationPermission.status.shouldShowRationale) {
                        showRationalDialog.value = true
                    } else {
                        locationPermission.launchPermissionRequest()
                    }
                } else {

                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location: Location? ->
                            if (location != null) {

                                val latitude = location.latitude
                                val longitude = location.longitude
                                getLocationName(latitude,longitude,context){
                                    locationName->
                                    locationState=locationName
                                    permeationGranted(true,latitude,longitude)
                                }
                            }
                        }
                        .addOnFailureListener {
                            permeationGranted(false,0.0,0.0)
                        }

                }
            }

        )
    }


}
private fun getLocationName(lat: Double, lon: Double, context: Context, locationName: (String) -> Unit) {
    val geocoder = Geocoder(context, Locale.getDefault())
    try {
        val addresses: List<Address> = geocoder.getFromLocation(lat, lon, 1) as List<Address>
        if (addresses.isNotEmpty()) {
            val address: Address = addresses[0]
            val locationName = address.getAddressLine(0)  // Get the full address
            val city = address.locality  // Get the city
            val country = address.countryName  // Get the country
            locationName("$country, $city")
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
