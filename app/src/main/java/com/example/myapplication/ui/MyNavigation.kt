package com.example.myapplication.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.example.myapplication.ui.enter_screen.EnterScreen
import com.example.myapplication.ui.log_in.Login
import com.example.myapplication.ui.menu.MenuMain
import com.example.myapplication.ui.profile.Profile
import com.example.myapplication.ui.reusable_content.MyBottomNavigationBar
import com.example.myapplication.ui.search_category.SearchCategory
import com.example.myapplication.ui.search_screen.SearchScreen
import com.example.myapplication.ui.show_ticket_details.ShowTicketDetails
import com.example.myapplication.ui.sign_up.SignUp
import com.example.myapplication.ui.tickets.Tickets
import com.example.myapplication.ui.title.TitleSplashScreen

@Composable
fun MyNavigation() {
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
                Login{
                    navController.navigate("menu")
                }
            }
            composable("signup"){
                SignUp()
            }
            composable("menu"){
                MenuMain()
            }
            composable(route = "tickets") {
                Tickets { navigationId ->
                    navController.navigate("show_ticket_details/$navigationId")
                }
            }
            composable(
                route = "show_ticket_details/{navigationId}",
                arguments = listOf(
                    navArgument("navigationId") {
                        type = NavType.StringType
                    },
                ),
            ){
                ShowTicketDetails()
            }
            composable("search"){
                SearchScreen()
            }
            composable("search-category"){
                SearchCategory()
            }
            composable("profile"){
                Profile()
            }

        }
    }

}