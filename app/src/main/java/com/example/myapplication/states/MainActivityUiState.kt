package com.example.myapplication.states

data class MainActivityUiState(
    var userNameText: String = "",
    var passwordText: String = "",
    var isJsBotEnable: Boolean = false,
    var passwordShow: Boolean = false,
    var passwordError: String = "",
    var userNameError: String = "",
    var isLoading: Boolean = false,
    var isNavigate: Boolean = false
) {
    fun emptyState() = MainActivityUiState()
}
