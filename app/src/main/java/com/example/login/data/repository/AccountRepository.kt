package com.example.login.data.repository

import com.example.login.data.model.Account
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository @Inject constructor() {
    private var accounts = mutableListOf<Account>()

    init {
        accounts.add(Account(username = "usuario1@gmail.com", password = "12345"))
        accounts.add(Account(username = "usuario2@gmail.com", password = "12345"))
        accounts.add(Account(username = "prueba@gmail.com", password = "12345"))
    }

    fun addAccount(account: Account) {
        if (accounts.none { it.username == account.username }) {
            accounts.add(account)
        }
    }

    fun getAccounts(): List<Account> = accounts.toList()

    fun accountExists(username: String): Boolean {
        return accounts.any { it.username == username }
    }

    fun getAccount(username: String): Account? {
        return accounts.find { it.username == username }
    }
}
