package com.example.prayerstime.presentation.home.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prayerstime.R

//
@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    date: String,
    location: String,
    nextPray: () -> Unit,
    previousPray: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Icon(painterResource(R.drawable.back), "",
            modifier = modifier
                .size(35.dp)
                .clickable { previousPray() })
        Column(
            modifier = modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = date, modifier = Modifier, fontSize = 25.sp)
            Spacer(Modifier.padding(vertical = 2.dp))
            Text(location, modifier = Modifier.padding(), fontSize = 15.sp)
        }

        Icon(painterResource(R.drawable.next), "",
            modifier = modifier
                .size(35.dp)
                .clickable { nextPray() })

    }
}