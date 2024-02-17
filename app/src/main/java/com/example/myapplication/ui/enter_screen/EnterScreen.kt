package com.example.myapplication.ui.enter_screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.reusable_content.FilledContentButton
import com.example.myapplication.ui.reusable_content.OutlinedContentButton
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun EnterScreen(
    navigateToLogin:()->Unit,
    navigateToSignUp:()->Unit
) {

        Box(
            modifier = Modifier
                //.padding(it)
                .fillMaxSize()
        ){
            Image(
                painter = painterResource(id = R.drawable.title),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize().padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(135.dp))
                Image(
                    painter = painterResource(id = R.drawable.group_278),
                    contentDescription = null,
                    modifier = Modifier.size(248.dp,118.dp)
                )
                Spacer(modifier = Modifier.height(385.dp))
                FilledContentButton(name = "Login", navigate = navigateToLogin)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedContentButton(name = "Register", navigate = navigateToSignUp)
            }
        }
    }




@Preview(showBackground = true)
@Composable
fun PreviewEnterScreen() {
    MyApplicationTheme {
        //EnterScreen()
    }
}