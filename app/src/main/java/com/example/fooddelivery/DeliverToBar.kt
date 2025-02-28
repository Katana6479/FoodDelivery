package com.example.fooddelivery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddelivery.ui.theme.TextRed

@Composable
fun DelverToBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ){
            Icon(
                modifier = Modifier.size(25.dp),
                painter = painterResource(R.drawable.positions_icon),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier.weight(4f)
        ){
            Box(
            ){
                Text(
                    text = "Your location",
                    color = TextRed,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp
                )
            }
            Box(
            ){
                Text(
                    text = "Moscow, Red Square",
                    color = Color.Black,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp
                )
            }
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ){
            Icon(
                modifier = Modifier.size(25.dp),
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null
            )
        }
    }
}