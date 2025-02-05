package com.example.login.ui.main

import com.example.login.navigation.HomeGraph
import com.example.login.navigation.LoginGraph

data class MainState(
    val activeAccount: Boolean
) {
    fun startDestination(): String {
        return if (activeAccount) HomeGraph.ROUTE else LoginGraph.ROUTE
    }
}
