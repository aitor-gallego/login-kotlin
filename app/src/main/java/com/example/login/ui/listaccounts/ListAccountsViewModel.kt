package com.example.login.ui.listaccounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.data.model.Account
import com.example.login.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListAccountViewModel @Inject constructor(
    private val repository: AccountRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ListAccountState())
    val state: StateFlow<ListAccountState> = _state

    fun loadAccounts() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val accounts = repository.getAccounts()
                _state.value = _state.value.copy(accounts = accounts, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    errorMessage = "Error al cargar las cuentas"
                )
            }
        }
    }

    fun deleteAccount(account: Account) {
        viewModelScope.launch {
            try {
                repository.deleteAccount(account)
                _state.value = _state.value.copy(
                    accounts = _state.value.accounts.filter { it.email != account.email }
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(errorMessage = "Error al eliminar la cuenta")
            }
        }
    }

    fun onSort() {
        val sortedAccounts = if (_state.value.isAscending) {
            _state.value.accounts.sortedBy { it.email }
        } else {
            _state.value.accounts.sortedByDescending { it.email }
        }
        _state.value.isAscending = !_state.value.isAscending
        _state.value = _state.value.copy(accounts = sortedAccounts)
    }
}
