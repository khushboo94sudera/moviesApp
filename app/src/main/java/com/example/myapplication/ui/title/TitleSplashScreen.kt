package com.example.myapplication.ui.title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay

@Composable
fun TitleSplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000) // Simulate a 2-second delay for the splash screen
        navController.navigate("mainScreen")
    }

        Box(
            modifier = Modifier
                //.padding(it)
                .fillMaxSize()
                .background(color = Color.Black)
        ){
            Image(
                painter = painterResource(id = R.drawable.title),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.group_278),
                    contentDescription = null,
                    modifier = Modifier.size(248.dp,118.dp)
                )
            }
        }
    }


@Preview( showBackground = true)
@Composable
fun PreviewTitleSplashScreen() {
    MyApplicationTheme {
        //TitleSplashScreen()
    }
}