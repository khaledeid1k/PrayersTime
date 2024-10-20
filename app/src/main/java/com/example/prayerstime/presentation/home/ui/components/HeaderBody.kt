package com.example.prayerstime.presentation.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prayerstime.R

@Composable
fun HeaderBody(modifier: Modifier = Modifier, nextPray:String, remainTimeHour:Int,remainTimeMinute:Int
               ) {

    Row (horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)){
        Text(stringResource(R.string.next_pray),modifier= Modifier,fontSize=20.sp)
        Text(nextPray,modifier= Modifier,fontSize=20.sp, fontWeight = FontWeight.Bold)
        Column(modifier = modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(R.string.time_left),modifier= Modifier,fontSize=20.sp)
            Spacer(Modifier.padding(vertical = 2.dp))
            Text(text = "${remainTimeHour}hr ${remainTimeMinute}min",modifier= Modifier,fontSize=15.sp)
        }

    }
}
