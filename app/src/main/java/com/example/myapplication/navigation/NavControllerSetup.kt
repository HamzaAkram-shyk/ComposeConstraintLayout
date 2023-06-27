package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.MainViewModel
import com.example.myapplication.routes.ScreenRoute
import com.example.myapplication.screencomposables.DetailsScreen
import com.example.myapplication.screencomposables.SignInScreen


@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = ScreenRoute.SignInScreen.route) {
        composable(route = ScreenRoute.SignInScreen.route) {
            val viewModel = viewModel<MainViewModel>()
            SignInScreen(viewModel = viewModel, navController = navController)

        }

        composable(route = ScreenRoute.SignUpScreen.route) {
            DetailsScreen()
        }
    }
}