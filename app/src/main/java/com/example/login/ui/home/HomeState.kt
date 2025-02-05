package com.example.login.ui.home

import com.example.login.navigation.HomeGraph
import com.example.login.navigation.LoginGraph

data class HomeState(
    val activeAccount: Boolean
) {
    fun startDestination(): String {
        return if (activeAccount) HomeGraph.ROUTE else LoginGraph.ROUTE
    }
}
