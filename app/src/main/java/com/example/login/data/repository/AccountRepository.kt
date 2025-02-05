package com.example.login.data.repository

import com.example.login.data.model.Account

object AccountRepository {
    private val accounts = mutableListOf<Account>()

    init {
        // Cuentas iniciales para pruebas
        accounts.add(Account(username = "usuario1@gmail.com", password = "12345"))
        accounts.add(Account(username = "usuario2@gmail.com", password = "12345"))
        accounts.add(Account(username = "prueba@gmail.com", password = "12345"))
    }

    // Agregar una nueva cuenta al repositorio
    fun addAccount(account: Account) {
        if (accounts.none { it.username == account.username }) {
            accounts.add(account)
        }
    }

    // Obtener todas las cuentas
    fun getAccounts(): List<Account> = accounts.toList()

    // Verificar si una cuenta ya existe
    fun accountExists(username: String): Boolean {
        return accounts.any { it.username == username }
    }

    // Obtener una cuenta por su username
    fun getAccount(username: String): Account? {
        return accounts.find { it.username == username }
    }
}
