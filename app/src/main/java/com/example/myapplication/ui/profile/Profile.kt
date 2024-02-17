package com.example.myapplication.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.reusable_content.FilledContentButtonWithIcon
import com.example.myapplication.ui.reusable_content.HeadingText

@Composable
fun Profile() {
        Box(
            modifier = Modifier
                //.padding(it)
                .fillMaxSize()
                .background(color = Color.Black)
                .verticalScroll(rememberScrollState())
        ){
            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                HeadingText(name = "Profile")
                Spacer(modifier = Modifier.height(24.dp))
                ImageBox()
                Spacer(modifier = Modifier.height(30.dp))
                Divider(modifier = Modifier.height(2.dp), color = Color(0xffA3A3A3))
                Spacer(modifier = Modifier.height(30.dp))
                FilledContentButtonWithIcon(name = "Edit profile", icon = R.drawable.icon_3)
                Spacer(modifier = Modifier.height(16.dp))
                FilledContentButtonWithIcon(name = "Payment options", icon = R.drawable.icon_4)
                Spacer(modifier = Modifier.height(16.dp))
                FilledContentButtonWithIcon(name = "Purchases history", icon = R.drawable.icon_5)
                Spacer(modifier = Modifier.height(16.dp))
                FilledContentButtonWithIcon(name = "User Terms of Service", icon = R.drawable.icon_6)
            }
        }
    }


@Composable
fun ImageBox() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(226.dp)
                .border(
                    BorderStroke(width = 10.dp, color = Color(0xffA21313)),
                    shape = RoundedCornerShape(100)
                )
        ){
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    //.padding(20.dp),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Wellington Castro",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}