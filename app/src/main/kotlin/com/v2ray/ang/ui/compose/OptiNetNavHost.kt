package com.v2ray.ang.ui.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.v2ray.ang.ui.auth.login.navigation.loginRoute
import com.v2ray.ang.ui.auth.login.navigation.registerRoute

@Composable
fun OptiNetNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        loginRoute(navController = navController)
        registerRoute(navController = navController)
    }
}