package com.v2ray.ang.ui.auth.register.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.v2ray.ang.R

@Composable
fun RegisterText() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.register),
        fontFamily = FontFamily(Font(R.font.iran_sans)),
        fontSize = 30.sp,
        textAlign = TextAlign.Center,
        color = Color(R.color.purple)
    )
}
