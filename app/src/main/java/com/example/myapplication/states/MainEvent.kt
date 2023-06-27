package com.example.myapplication.states

sealed class MainEvent {
    data class ChangeUserNameText(val newText: String) : MainEvent()
    data class ChangePasswordText(val newText: String) : MainEvent()
    object ToggleButton : MainEvent()
    object TogglePassword : MainEvent()
    object Loading : MainEvent()

    object LoginClick : MainEvent()

    object Navigate : MainEvent()
}
