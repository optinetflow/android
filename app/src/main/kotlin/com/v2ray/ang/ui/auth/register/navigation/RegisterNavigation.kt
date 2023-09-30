package com.v2ray.ang.ui.auth.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.v2ray.ang.ui.auth.register.RegisterRoute
import com.v2ray.ang.util.NavRoutes

fun NavGraphBuilder.registerRoute(navController: NavHostController) {
    composable(route = NavRoutes.register_route) {
        RegisterRoute(onLoginClick = { navController.navigate(NavRoutes.login_route) })
    }
}
