package com.v2ray.ang.util.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    progress: Float = 0.75f,
    strokeWidth: Dp = 8.dp
) {
    Column(modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator(
            progress = progress,
            strokeWidth = strokeWidth
        )
    }
}