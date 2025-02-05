package com.example.login.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    var state by mutableStateOf(LoginState())

    fun onEmailChange(newEmail: String) {
        state = state.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        state = state.copy(password = newPassword)
    }

    fun onLogin() {
        val currentState = state

        if (currentState.email.isEmpty() || currentState.password.isEmpty()) {
            state = currentState.copy(errorMessage = "Por favor, completa todos los campos.")
            return
        }

        val account = accountRepository.getAccount(currentState.email)

        if (account == null) {
            state = currentState.copy(errorMessage = "El usuario no existe.")
            return
        }

        if (account.password != currentState.password) {
            state = currentState.copy(errorMessage = "Credenciales incorrectas.")
            return
        }

        state = currentState.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            delay(2000)
            state = currentState.copy(isLoading = false)
        }
    }

    fun onArgsNav(email: String, password: String) {
        state = state.copy(
            email = email,
            password = password
        )
    }
}