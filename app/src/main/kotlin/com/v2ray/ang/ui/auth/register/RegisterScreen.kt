package com.v2ray.ang.ui.auth.register

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.v2ray.ang.ui.auth.login.components.FirstNameTextField
import com.v2ray.ang.ui.auth.login.components.PasswordTextField
import com.v2ray.ang.ui.auth.login.components.PhoneTextField
import com.v2ray.ang.ui.auth.register.components.LastNameTextField
import com.v2ray.ang.ui.auth.register.components.PasswordVerifyTextField
import com.v2ray.ang.ui.auth.register.components.RegisterButton
import com.v2ray.ang.ui.auth.register.components.RegisterText

@Composable
fun RegisterRoute(
    registerViewModel: RegisterViewModel = hiltViewModel(),
    onLoginClick: () -> Unit
) {
    val registerState = registerViewModel.registerState.collectAsStateWithLifecycle()
    RegisterScreen(
        registerState = registerState.value,
        onRegisterClick = registerViewModel::register,
        onLoginClick = onLoginClick
    )
}

@Composable
internal fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerState: RegisterState,
    onRegisterClick: (String, String, String, String) -> Unit,
    onLoginClick: () -> Unit
) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVerify = remember { mutableStateOf("") }

    val firstNameEmpty = remember { mutableStateOf(false) }
    val lastNameEmpty = remember { mutableStateOf(false) }
    val phoneEmpty = remember { mutableStateOf(false) }
    val passwordEmpty = remember { mutableStateOf(false) }
    val passwordVerifyEmpty = remember { mutableStateOf(false) }

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
                modifier = modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RegisterText()
                Spacer(modifier = modifier.height(10.dp))

                FirstNameTextField(
                    firstName = { firstName.value = it },
                    isErrorEnabled = firstNameEmpty.value
                )
                LastNameTextField(
                    lastName = { lastName.value = it },
                    isErrorEnabled = lastNameEmpty.value
                )
                PhoneTextField(
                    phoneValue = { phone.value = it },
                    isErrorEnabled = lastNameEmpty.value
                )
                PasswordTextField(
                    passwordValue = { password.value = it },
                    isErrorEnabled = passwordEmpty.value
                )
                PasswordVerifyTextField(passwordValue = { passwordVerify.value = it })

                Spacer(modifier = modifier.height(15.dp))
                RegisterButton {
                    if (firstName.value.isEmpty()) println("firstName empty")
                    if (lastName.value.isEmpty()) println("LastName empty")
                    if (phone.value.isEmpty()) println("phone empty")

                    if (password.value.isEmpty()) println("pass 1 empty")
                    if (passwordVerify.value.isEmpty()) println("pass 2 empty")
                }
            }
        }

        TextButton(
            modifier = modifier
                .padding(20.dp)
                .align(Alignment.BottomCenter),
            onClick = onLoginClick
        ) {
            Text(
                text = stringResource(R.string.already_have_account_go_to_login),
                fontFamily = FontFamily(Font(R.font.iran_sans)),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview(showBackground = true, locale = "fa")
private fun RegisterScreenPreview() {
    RegisterScreen(
        registerState = RegisterState.Loading(isLoading = false),
        onLoginClick = {},
        onRegisterClick = { _, _, _, _ -> }
    )
}