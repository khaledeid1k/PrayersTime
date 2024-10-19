package com.example.prayerstime.presentation.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prayerstime.utils.convertTo12HourFormat

@Composable
fun PrayItem(prayName: String,
    prayTime: String
) {
    Card(modifier= Modifier .padding(10.dp),
        shape = RoundedCornerShape(8.dp)
       ) {
        Row (horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical =16.dp)){
        Text(prayName,fontSize=16.sp)
        Text(prayTime.convertTo12HourFormat())
    } }

}