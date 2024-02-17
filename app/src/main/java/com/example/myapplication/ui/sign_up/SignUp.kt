package com.example.myapplication.ui.sign_up
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.reusable_content.FilledContentButton
import com.example.myapplication.ui.reusable_content.HeadingText
import com.example.myapplication.ui.reusable_content.HorizontalOrDivider
import com.example.myapplication.ui.reusable_content.MediumHeadingText
import com.example.myapplication.ui.reusable_content.OutlinedContentButtonWithIcon
import com.example.myapplication.ui.reusable_content.TextBoxContent
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun SignUp() {

        Box(
            modifier = Modifier
                .fillMaxSize()
                //.padding(it)
                .background(color = Color.Black)
        ){
            Column(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,

                ) {
                Spacer(modifier = Modifier.height(60.dp))
                HeadingText(name = "Register")
                Spacer(modifier = Modifier.height(16.dp))
                MediumHeadingText(name = "Name")
                Spacer(modifier = Modifier.height(8.dp))
                TextBoxContent(name = "Enter your Full Name")
                Spacer(modifier = Modifier.height(16.dp))
                MediumHeadingText(name = "Email")
                Spacer(modifier = Modifier.height(8.dp))
                TextBoxContent(name = "Enter your Email")
                Spacer(modifier = Modifier.height(16.dp))
                MediumHeadingText(name = "User")
                Spacer(modifier = Modifier.height(8.dp))
                TextBoxContent(name = "Enter your username")
                Spacer(modifier = Modifier.height(16.dp))
                MediumHeadingText(name = "Password")
                Spacer(modifier = Modifier.height(8.dp))
                TextBoxContent(name = "Enter your Password")
                Spacer(modifier = Modifier.height(16.dp))
                //FilledContentButton(name = "Register")
                Spacer(modifier = Modifier.height(22.dp))
                HorizontalOrDivider(name = "Or")
                Spacer(modifier = Modifier.height(22.dp))
                OutlinedContentButtonWithIcon(
                    name = "Register in with Gmail",
                    icon = R.drawable.logos_google_icon
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedContentButtonWithIcon(
                    name = "Register in with Facebook",
                    icon = R.drawable.logos_facebook
                )
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun PreviewSignUp() {
    MyApplicationTheme {
        SignUp()
    }
}