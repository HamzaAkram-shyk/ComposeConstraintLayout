package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.states.MainActivityUiState
import com.example.myapplication.states.MainEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
 val uiStateFlow = MutableStateFlow(MainActivityUiState())
    val stateFlow = uiStateFlow.asStateFlow()

    private val _events = MutableStateFlow<MainEvent>(MainEvent.Loading)
    val event = _events.asStateFlow()

    fun onEventTriggered(newEvent: MainEvent) {
        when (newEvent) {
            is MainEvent.ChangePasswordText -> {
                uiStateFlow.update { currentState ->
                    if (newEvent.newText.isNotEmpty() && currentState.isJsBotEnable)
                        return@update currentState.copy(
                            passwordText = newEvent.newText,
                        )
                    if (newEvent.newText.isEmpty())
                        return@update currentState.copy(
                            passwordText = "",
                            isJsBotEnable = false
                        )
                    currentState.copy(passwordText = newEvent.newText)

                }
            }

            is MainEvent.ChangeUserNameText -> {
                uiStateFlow.update {
                    if (newEvent.newText.isEmpty())
                        return@update it.copy(
                            userNameText = newEvent.newText,
                            isJsBotEnable = false
                        )
                    if (newEvent.newText.isNotEmpty() && !it.isJsBotEnable)
                        return@update it.copy(
                            userNameText = newEvent.newText,
                            isJsBotEnable = newEvent.newText.isNotEmpty()
                        )
                    it.copy(
                        userNameText = newEvent.newText,
                    )
                }

            }

            MainEvent.TogglePassword -> {
                uiStateFlow.update { currentState ->
                    currentState.copy(
                        passwordShow = !currentState.passwordShow,
                        passwordError = if (currentState.passwordShow && currentState.passwordText.length < 8) "Password should be least 8 character" else ""
                    )
                }
            }

            MainEvent.Loading -> {
                uiStateFlow.update { it.copy(isLoading = true) }
            }

            MainEvent.LoginClick -> {
                uiStateFlow.update { return@update it.copy(isLoading = true) }
                login {
                    uiStateFlow.update { currentState ->
                        if (currentState.userNameText.isEmpty()) {
                            return@update currentState.copy(
                                userNameError = " user name can't be empty",
                                isLoading = false,
                            )

                        }

                        if (currentState.passwordText.isEmpty()) {
                            return@update currentState.copy(
                                passwordError = "Password Can't be empty",
                                isLoading = false,
                                userNameError = ""
                            )

                        }

                        if (currentState.passwordText.count { it.isDigit() } < 3) {
                            return@update currentState.copy(
                                passwordError = "password must contains three digit",
                                isLoading = false
                            )
                        }


                        return@update currentState.copy(
                            isLoading = false,
                            userNameError = "",
                            passwordError = "",
                            isNavigate = true
                        )

                    }
                }

            }

            else -> {}
        }
    }

    private fun login(onCall: () -> Unit) {
        viewModelScope.launch {
            delay(4000L)
//            uiStateFlow.update { it.copy(isLoading = false) }
            onCall()
        }
    }


}