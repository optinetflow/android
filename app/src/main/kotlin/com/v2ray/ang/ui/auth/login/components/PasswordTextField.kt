package com.v2ray.ang.ui.auth.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.v2ray.ang.R

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    passwordValue: (String) -> Unit
) {
    val password = rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val hasError = rememberSaveable { mutableStateOf(false) }
    val maxChar = 16

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        value = password.value,
        onValueChange = {
            if (it.length <= maxChar) {
                password.value = it
                passwordValue(it)
            }
        },
        label = { TextLabel() },
        textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.iran_sans))),
        singleLine = true,
        trailingIcon = {
            val image = if (passwordVisible) {
                painterResource(R.drawable.baseline_visibility_24)
            } else {
                painterResource(R.drawable.baseline_visibility_off_24)
            }

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Image(
                    painter = image,
                    contentDescription = null
                )
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = { LeadingIcon() },
        isError = hasError.value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorCursorColor = Color.Transparent,
            cursorColor = Color.Transparent
        )
    )
}

@Composable
private fun LeadingIcon() {
    Image(
        painter = painterResource(R.drawable.baseline_lock_24),
        contentDescription = null
    )
}

@Composable
private fun TextLabel() {
    Text(
        text = stringResource(R.string.password),
        fontFamily = FontFamily(Font(R.font.iran_sans))
    )
}

@Composable
@Preview(locale = "fa")
private fun PasswordTextFieldPreview() {
    PasswordTextField(passwordValue = {})
}