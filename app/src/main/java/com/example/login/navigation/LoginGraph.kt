package com.example.login.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.login.ui.login.LoginScreen
import com.example.login.ui.login.LoginViewModel
import com.example.login.ui.signup.SignUpScreen
import com.example.login.ui.signup.SignUpViewModel

object LoginGraph {
    const val ROUTE = "login_graph"

    const val EMAIL = "email"
    const val PASSWORD = "password"

    fun login() = "$ROUTE/login?$EMAIL={email}&$PASSWORD={password}"

    fun signUp() = "$ROUTE/signup"
}

fun NavGraphBuilder.loginGraph(navController: NavController) {
    navigation(startDestination = LoginGraph.login(), route = LoginGraph.ROUTE) {
        login(navController)
        signUp(navController)
    }
}

private fun NavGraphBuilder.login(navController: NavController) {
    composable(
        route = LoginGraph.login(),
        arguments = listOf(
            navArgument(LoginGraph.EMAIL) { type = NavType.StringType; defaultValue = "" },
            navArgument(LoginGraph.PASSWORD) { type = NavType.StringType; defaultValue = "" }
        )
    ) { backStackEntry ->
        val email = backStackEntry.arguments?.getString(LoginGraph.EMAIL) ?: ""
        val password = backStackEntry.arguments?.getString(LoginGraph.PASSWORD) ?: ""

        val loginVM: LoginViewModel = hiltViewModel()
        LoginScreen(
            email = email,
            password = password,
            viewModel = loginVM,
            goToSignUp = { navController.navigate(LoginGraph.signUp()) },
            goToAccountList = { navController.navigate(HomeGraph.listAccount()) }
        )
    }
}

private fun NavGraphBuilder.signUp(navController: NavController) {
    composable(route = LoginGraph.signUp()) {
        val signUpVM: SignUpViewModel = hiltViewModel()
        SignUpScreen(
            viewModel = signUpVM,
            goUp = { email, password ->
                navController.navigate(
                    LoginGraph.login().replace("{email}", email).replace("{password}", password)
                )
            }
        )
    }
}
