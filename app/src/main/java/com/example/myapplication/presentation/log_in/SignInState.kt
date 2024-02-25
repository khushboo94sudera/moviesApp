package com.example.myapplication.presentation.log_in

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
