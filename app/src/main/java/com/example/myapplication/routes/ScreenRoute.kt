package com.example.myapplication.routes

sealed class ScreenRoute(val route: String) {
    object SignInScreen : ScreenRoute(route = "sigIn")
    object SignUpScreen : ScreenRoute(route = "signUp")
}
