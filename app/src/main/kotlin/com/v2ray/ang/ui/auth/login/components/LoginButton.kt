package com.v2ray.ang.ui.auth.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.v2ray.ang.R

@Composable
fun LoginButton(
    onLoginClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        onClick = onLoginClick,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = Color(R.color.purple)
        )
    ) {
        Text(
            text = stringResource(R.string.action_sign_in_short),
            fontFamily = FontFamily(Font(R.font.iran_sans)),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
@Preview
private fun LoginButton() {
    LoginButton(onLoginClick = {})
}