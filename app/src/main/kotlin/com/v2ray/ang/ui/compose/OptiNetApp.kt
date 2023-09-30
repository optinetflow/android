package com.v2ray.ang.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.v2ray.ang.util.NavRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptiNetApp(
    modifier: Modifier = Modifier,
    appState: OptiNetAppState = rememberOptiNetAppState(),
    startDestination: String = NavRoutes.login_route,
) {
    val navController = appState.navController
    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = { TopAppBar(title = { Text(text = "OptiNet") }) },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            OptiNetNavHost(
                navController = navController,
                startDestination = startDestination
            )
        }
    }
}

@Composable
@Preview
fun OptiNetAppPreview() {
    OptiNetApp()
}
