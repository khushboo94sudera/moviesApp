package com.example.myapplication.ui.edit_profile

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.log_in.UserData
import com.example.myapplication.ui.reusable_content.HeadingText
import com.example.myapplication.ui.reusable_content.IconBack
import com.example.myapplication.ui.reusable_content.MediumHeadingText
import com.example.myapplication.ui.reusable_content.TextBoxContent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

@Composable
fun EditProfile(
    userData: UserData?,
    navigateToProfile: (String, String) -> Unit,
    navigationCallBack:()->Unit
) {
    var fullName by remember { mutableStateOf(userData?.userName ?: "") }
    var email by remember { mutableStateOf(userData?.userEmail ?: "") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            //.padding(it)
            .background(color = Color.Black)
            .verticalScroll(rememberScrollState())
    ){
        Column(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Row {
                IconBack(navigationCallback = navigationCallBack)
                Spacer(modifier = Modifier.height(10.dp))
                HeadingText(name = "Edit Profile")
            }

            Spacer(modifier = Modifier.height(16.dp))
            MediumHeadingText(name = "Name")
            Spacer(modifier = Modifier.height(8.dp))
            TextBoxContent(
                name = "Enter your Full Name",
                value = fullName,
                onValueChange = {fullName = it}
            )
            Spacer(modifier = Modifier.height(16.dp))
            MediumHeadingText(name = "Email")
            Spacer(modifier = Modifier.height(8.dp))
            TextBoxContent(
                name = "Enter your Email",
                value = email,
                onValueChange = {email = it}
            )
            Spacer(modifier = Modifier.height(16.dp))
            MediumHeadingText(name = "User")
            Spacer(modifier = Modifier.height(8.dp))
            TextBoxContent(
                name = "Enter your username",
                value = fullName,
                onValueChange = {fullName = it}
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Update user's profile information

                        navigateToProfile(fullName, email) // Pass newEmail to onSaveChanges

                },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(Color(0xffA21313)),

                ){
                Text(
                    text = "Save Changes",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.height(22.dp))
        }
    }
}