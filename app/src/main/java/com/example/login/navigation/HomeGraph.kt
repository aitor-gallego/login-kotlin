package com.example.login.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import androidx.navigation.compose.composable
import com.example.login.ui.listaccounts.ListAccountScreen
import com.example.login.ui.listaccounts.ListAccountViewModel

object HomeGraph {
    const val ROUTE = "home_graph"
    fun listAccount() = "$ROUTE/accountList"
}


fun NavGraphBuilder.homeGraph(
    navController: NavController,
) {
    navigation(startDestination = HomeGraph.listAccount(), route = HomeGraph.ROUTE) {
        listAccount(navController)
    }
}

private fun NavGraphBuilder.listAccount(
    navController: NavController
) {
    composable(route = HomeGraph.listAccount()) {
        val listAccountViewModel: ListAccountViewModel = hiltViewModel()
        ListAccountScreen(
            viewModel = listAccountViewModel,
            goToCreation = {},
            goToDetail = {},
            openDrawer = {}
        )
    }
}