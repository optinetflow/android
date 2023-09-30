package com.v2ray.ang.ui.auth.register.components

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
import com.v2ray.ang.ui.auth.login.components.PasswordTextField

@Composable
fun PasswordVerifyTextField(
    modifier: Modifier = Modifier,
    passwordValue: (String) -> Unit
) {
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisible by rememberSaveable { mutableStateOf(false) }
    val isPasswordValid = password.value.length > 6

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        value = password.value,
        onValueChange = {
            password.value = it
            if (it.length > 6) passwordValue(it)
        },
        label = { TextLabel() },
        textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.iran_sans))),
        singleLine = true,
        trailingIcon = { TrailingIcon(passwordVisible) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = { LeadingIcon() },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
private fun TrailingIcon(passwordVisible: Boolean) {
    var passwordVisible1 = passwordVisible
    val image = if (passwordVisible1)
        painterResource(R.drawable.baseline_visibility_24)
    else painterResource(R.drawable.baseline_visibility_off_24)

    IconButton(onClick = { passwordVisible1 = !passwordVisible1 }) {
        Image(
            painter = image,
            contentDescription = null
        )
    }
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
        text = stringResource(R.string.repeat_password_again),
        fontFamily = FontFamily(Font(R.font.iran_sans))
    )
}

@Composable
@Preview(locale = "fa")
private fun PasswordVerifyTextFieldPreview() {
    PasswordTextField(passwordValue = {})
}