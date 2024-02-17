package com.example.myapplication.ui.show_ticket_details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.reusable_content.IconBack

@Composable
fun ShowTicketDetails() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                //.padding(it)
                .background(color = Color.Black)
        ){
            Image(
                painter = painterResource(id = R.drawable.pic_16),
                contentDescription = null,
                modifier = Modifier.size(width = 390.dp, height = 522.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black),
                            startY = 400f,
                            endY = 1400f
                        )
                    )
            )
            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(76.dp))
                IconBack()
                //Spacer(modifier = Modifier.height(136.dp))
                Image(
                    painter = painterResource(id = R.drawable.mask_1),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 270.dp, height = 216.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Image(
                        painter = painterResource(id = R.drawable.icon_1),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "São Paulo, SP - Allianz Parque",
                        style = MaterialTheme.typography.labelMedium.copy(color = Color.White),
                        modifier = Modifier
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_2),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "24/07/2023 - Opening: 20:00 (BRT) ",
                        style = MaterialTheme.typography.labelMedium.copy(color = Color.White),
                        modifier = Modifier
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "QR code",
                    style = MaterialTheme.typography.titleSmall.copy(color = Color.White,fontWeight = FontWeight.SemiBold),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()

                        .border(
                            BorderStroke(width = 1.dp, color = Color(0xffA21313)),
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 26.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.qr_code_1),
                            contentDescription = null,
                            modifier = Modifier.size(198.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
