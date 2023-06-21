package com.example.myapplication.states

data class MainActivityUiState(
    var userNameText: String = "",
    var passwordText: String = "",
    var isJsBotEnable: Boolean = false,
    var passwordShow: Boolean = false,
)
