package com.example.myapplication.ui

import android.app.Activity.RESULT_OK
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.example.myapplication.presentation.log_in.GoogleAuthUiClient
import com.example.myapplication.ui.enter_screen.EnterScreen
import com.example.myapplication.presentation.log_in.Login
import com.example.myapplication.presentation.log_in.SignInViewModel
import com.example.myapplication.ui.menu.MenuMain
import com.example.myapplication.presentation.profile.Profile
import com.example.myapplication.ui.reusable_content.MyBottomNavigationBar
import com.example.myapplication.ui.search_category_2.SearchCategory
import com.example.myapplication.ui.search_screen_1.SearchScreen
import com.example.myapplication.ui.show.ShowContent
import com.example.myapplication.ui.show_ticket_details.ShowTicketDetails
import com.example.myapplication.presentation.sign_up.SignUp
import com.example.myapplication.ui.tickets.Tickets
import com.example.myapplication.ui.title.TitleSplashScreen
import kotlinx.coroutines.launch


@Composable
fun MyNavigation(
    lifecycleScope: LifecycleCoroutineScope,
    applicationContext: Context,
    googleAuthUiClient: GoogleAuthUiClient
) {

    val navController = rememberNavController()
    val routeNav by navController.currentBackStackEntryFlow.collectAsState(
        initial = navController
            .currentBackStackEntry,
    )
    val substrings = listOf("menu", "profile", "search","tickets","search-category")
    val route1 = routeNav?.destination?.route
    val containsAnySubstring = substrings.any { substring ->
        route1?.contains(substring) == true
    }
    Scaffold(
        modifier = Modifier,
        bottomBar = {
            MyBottomNavigationBar(
                showBottomBar = containsAnySubstring,
                route = routeNav?.destination?.route ?: "menu",
                navigate = {
                    navController.navigate(it){
                        navOptions {
                            popUpTo(0)
                        }
                    }
                }
            )
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = "splashScreen"
        ){
            composable("splashScreen"){
                TitleSplashScreen(navController = navController)
            }
            composable("mainScreen"){
                EnterScreen(
                    {navController.navigate("login")},
                    {navController.navigate("signup")}
                )
            }
            composable("login"){
                val viewModel = viewModel<SignInViewModel>()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = Unit) {
                    if(googleAuthUiClient.getSignedInUser() != null) {
                        navController.navigate("menu")
                    }
                }

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                    onResult = {result->
                        if (result.resultCode == RESULT_OK){
                            lifecycleScope.launch {
                                val signInResult = googleAuthUiClient.signInWithIntent(
                                    intent = result.data ?: return@launch
                                )
                                viewModel.onSignInResult(signInResult)
                            }
                        }
                    }
                )
                LaunchedEffect(key1 = state.isSignInSuccessful) {
                    if(state.isSignInSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "Sign in successful",
                            Toast.LENGTH_LONG
                        ).show()

                        navController.navigate("menu")
                        viewModel.resetState()
                    }
                }

                Login(
                    state = state,
                    navigateToMenu = {
                        lifecycleScope.launch {
                            val signInIntentSender = googleAuthUiClient.signIn()
                            launcher.launch(
                                IntentSenderRequest.Builder(
                                    signInIntentSender ?: return@launch
                                ).build()
                            )
                        }
                    }
                )
            }
            composable("signup"){
                SignUp()
            }
            composable("menu"){
                MenuMain{navigationId->
                    navController.navigate("showContent/$navigationId")
                }
            }
            composable(
                route = "showContent/{movie_id}",
                arguments = listOf(
                    navArgument("movie_id"){
                        type = NavType.StringType
                    }
                )
            ){
                ShowContent { navController.popBackStack() }
            }
            composable(route = "tickets") {
                Tickets { navigationId ->
                    navController.navigate("show_ticket_details/$navigationId")
                }
            }
            composable(
                route = "show_ticket_details/{ticket_id}",
                arguments = listOf(
                    navArgument("ticket_id") {
                        type = NavType.StringType
                    },
                ),
            ){
                ShowTicketDetails(navigationCallBack = { navController.popBackStack() })
            }
            composable("search"){
                SearchScreen{navigationId ->
                    navController.navigate("search-category/$navigationId")
                }
            }
            composable(
                route = "search-category/{band_id}",
                arguments = listOf(
                    navArgument("band_id"){
                        type = NavType.StringType
                    }
                )
            ){
                SearchCategory(
                    navigationCallBack = {navigationId->
                        navController.navigate("showContent/$navigationId") },
                    navigationCallBackForIconBack = {navController.popBackStack()},

                )
            }
            composable("profile"){
                Profile(
                    userData = googleAuthUiClient.getSignedInUser(),
                    onSignOut = {
                        lifecycleScope.launch {
                            googleAuthUiClient.signOut()
                            Toast.makeText(
                                applicationContext,
                                "Signed out",
                                Toast.LENGTH_LONG
                            ).show()

                            navController.popBackStack()
                        }
                    },
                    navigateToLogin = { navController.navigate("login") }
                )
            }

        }
    }

}