package com.example.login.ui.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.login.data.model.Account
import com.example.login.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AccountRepository
) : ViewModel() {

    var email = mutableStateOf("")
        private set
    var password = mutableStateOf("")
        private set

    val state = mutableStateOf(SignUpState())

    fun onEmailChange(newEmail: String) {
        email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        password.value = newPassword
    }

    fun registerAccount(): Boolean {
        if (email.value.isEmpty() || password.value.isEmpty()) {
            state.value = SignUpState(errorMessage = "Todos los campos son obligatorios")
            return false
        }

        if (!isValidEmail(email.value)) {
            state.value = SignUpState(errorMessage = "El correo electrónico no es válido")
            return false
        }

        if (password.value.length < 5) {
            state.value = SignUpState(errorMessage = "La contraseña debe tener más de 5 caracteres")
            return false
        }

        if (repository.accountExists(email.value)) {
            state.value = SignUpState(errorMessage = "Este usuario ya existe.")
            return false
        }

        repository.addAccount(Account(username = email.value, password = password.value))
        state.value = SignUpState(errorMessage = null)
        return true
    }

    fun getLastRegisteredCredentials(): Pair<String, String> {
        return Pair(email.value, password.value)
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
