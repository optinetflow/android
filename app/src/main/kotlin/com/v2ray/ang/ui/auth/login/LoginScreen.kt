package com.v2ray.ang.ui.auth.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.v2ray.ang.R
import com.v2ray.ang.ui.auth.login.components.LoginButton
import com.v2ray.ang.ui.auth.login.components.LoginText
import com.v2ray.ang.ui.auth.login.components.PasswordTextField
import com.v2ray.ang.ui.auth.login.components.PhoneTextField

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onRegisterClick: () -> Unit
) {
    val loginState = loginViewModel.loginState.collectAsStateWithLifecycle()
    LoginScreen(
        loginState = loginState.value,
        onLoginClick = loginViewModel::login,
        onRegisterClick = onRegisterClick
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginState: LoginState,
    onLoginClick: (String, String) -> Unit,
    onRegisterClick: () -> Unit
) {
    val phone = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    val phoneEmpty = remember { mutableStateOf(false) }
    val passwordEmpty = remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 35.dp, end = 35.dp)
                .align(Alignment.Center)
                .wrapContentSize(),
            shape = RoundedCornerShape(30.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginText()

                Spacer(modifier = Modifier.height(10.dp))

                PhoneTextField(phoneValue = { phone.value = it })
                Spacer(modifier = Modifier.height(10.dp))
                PasswordTextField(passwordValue = { password.value = it })

                Spacer(modifier = Modifier.height(25.dp))
                LoginButton(onLoginClick = {
                    if (phone.value.isEmpty()) phoneEmpty.value = true
                    if (password.value.isEmpty()) passwordEmpty.value = true

                    if (phone.value.isNotEmpty() && password.value.isNotEmpty()) {
                        onLoginClick(phone.value, password.value)
                    }
                })
            }
        }

        TextButton(
            modifier = modifier
                .padding(20.dp)
                .align(Alignment.BottomCenter),
            onClick = onRegisterClick
        ) {
            Text(
                text = stringResource(R.string.not_yet_registered_signup_now),
                fontFamily = FontFamily(Font(R.font.iran_sans)),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }

    ErrorHandle(phoneEmpty, passwordEmpty)
}

@Composable
fun ErrorHandle(
    phoneEmpty: MutableState<Boolean>,
    passwordEmpty: MutableState<Boolean>
) {
    val context = LocalContext.current
    when {
        phoneEmpty.value && passwordEmpty.value -> {
            Toast.makeText(
                context,
                stringResource(R.string.phone_and_password_is_empty),
                Toast.LENGTH_SHORT
            ).show()
        }

        phoneEmpty.value -> {
            Toast.makeText(context, stringResource(R.string.phone_is_empty), Toast.LENGTH_SHORT)
                .show()
        }

        passwordEmpty.value -> {
            Toast.makeText(context, stringResource(R.string.password_is_empty), Toast.LENGTH_SHORT)
                .show()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, locale = "fa")
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        loginState = LoginState.Loading,
        onLoginClick = { _, _ -> },
        onRegisterClick = {}
    )
}