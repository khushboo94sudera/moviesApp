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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.log_in.UserData
import com.example.myapplication.ui.reusable_content.FilledContentButton
import com.example.myapplication.ui.reusable_content.FilledContentButtonWithIcon
import com.example.myapplication.ui.reusable_content.HeadingText

@Composable
fun Profile(
    userData: UserData?,
    onSignOut: () -> Unit,
    navigateToLogin:()-> Unit
) {
        Box(
            modifier = Modifier
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
                ImageBox(userData)
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
                Spacer(modifier = Modifier.height(16.dp))
                if (userData?.userName == null){
                    FilledContentButton(name = "Sign In", navigate = { navigateToLogin() })
                }else{
                    FilledContentButton(name = "Sign Out", navigate = { onSignOut() })
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
@Composable
fun ImageBox(userData: UserData?) {
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
            if(userData?.profilePictureUrl != null) {
                AsyncImage(
                    model = userData.profilePictureUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(210.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        if(userData?.userName != null) {
            Text(
                text = userData.userName,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}