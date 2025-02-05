package com.example.login.ui.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.login.navigation.homeGraph
import com.example.login.navigation.loginGraph

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    NavHost(
        navController = navController,
        startDestination = viewModel.state.startDestination()
    ) {
        homeGraph(navController)
        loginGraph(navController)
    }
}
