package com.example.myapplication.ui.theme

import androidx.lifecycle.ViewModel
import com.example.myapplication.states.MainActivityUiState
import com.example.myapplication.states.MainEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel() : ViewModel() {
    private val uiStateFlow = MutableStateFlow(MainActivityUiState())
    val stateFlow = uiStateFlow.asStateFlow()

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.ChangePasswordText -> {
                uiStateFlow.update { currentState ->
                    if (event.newText.isNotEmpty() && currentState.isJsBotEnable)
                        return@update currentState.copy(
                            passwordText = event.newText,
                        )
                    if (event.newText.isEmpty())
                        return@update currentState.copy(
                            passwordText = "",
                            isJsBotEnable = false
                        )
                    currentState.copy(passwordText = event.newText)

                }
            }

            is MainEvent.ChangeUserNameText -> {
                uiStateFlow.update {
                    if (event.newText.isEmpty())
                        return@update it.copy(
                            userNameText = event.newText,
                            isJsBotEnable = false
                        )
                    if (event.newText.isNotEmpty() && !it.isJsBotEnable)
                        return@update it.copy(
                            userNameText = event.newText,
                            isJsBotEnable = event.newText.isNotEmpty()
                        )
                    it.copy(
                        userNameText = event.newText,
                    )
                }

            }

            MainEvent.TogglePassword -> {
                uiStateFlow.update { currentState ->
                    currentState.copy(passwordShow = !currentState.passwordShow)
                }
            }

            else -> {}
        }
    }


}