package com.example.fooddelivery.displays

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddelivery.R


@Composable
fun ProfileScreen(
    paddingValues: PaddingValues,
    onThemeChange: () -> Unit
){
    var checked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(R.drawable.profile_pic),
            contentDescription = null,
            modifier = Modifier
                .size(90.dp)
                .padding(top = 8.dp)
        )
        Text(
            text = "Food User",
            fontSize = 16.sp,
            fontWeight = FontWeight.W600
        )
        Card (
            modifier = Modifier
                .padding(32.dp),
            shape = RoundedCornerShape(12)

        ){
            Row(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
                    .height(42.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(Icons.Default.AccountCircle, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Personal data",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400
                )
            }
            Row(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
                    .height(42.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(Icons.Default.Notifications, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Notification",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400
                )
            }
            Row(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
                    .height(42.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(Icons.Default.MailOutline, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Support",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400
                )
            }
            Row(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
                    .height(42.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(Icons.Default.Info, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "About us",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Темная тема ",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W400
            )
            Switch(
                modifier = Modifier.size(50.dp),
                checked = checked,
                onCheckedChange = {
                    checked = it
                    onThemeChange()
                }
            )
        }
    }
}