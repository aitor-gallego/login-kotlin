package com.example.login.ui.listaccounts

import com.example.login.data.model.Account

data class ListAccountState(
    val accounts: List<Account> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)