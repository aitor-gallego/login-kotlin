package com.example.login.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.login.navigation.homeGraph
import com.example.login.navigation.loginGraph

@Composable
fun HomeScreen(navController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {
    NavHost(
        navController = navController,
        startDestination = viewModel.state.startDestination()
    ) {
        homeGraph(navController)
        loginGraph(navController)
    }
}
