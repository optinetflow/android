package com.v2ray.ang.ui.auth.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.v2ray.ang.ui.auth.login.LoginRoute
import com.v2ray.ang.util.NavRoutes

fun NavGraphBuilder.loginRoute(navController: NavHostController) {
    composable(route = NavRoutes.login_route) {
        LoginRoute(onRegisterClick = { navController.navigate(NavRoutes.register_route) })
    }
}