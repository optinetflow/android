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
    /*val registerState = registerViewModel.registerState.collectAsStateWithLifecycle() */
    RegisterScreen(
        // registerState = registerState.value,
        onLoginClick = onLoginClick
    )
}

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit
) {
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

                FirstNameTextField(firstName = {})
                LastNameTextField(lastName = {})
                PhoneTextField(phoneValue = { })
                PasswordTextField(passwordValue = { })
                PasswordVerifyTextField(passwordValue = { })

                Spacer(modifier = modifier.height(15.dp))
                RegisterButton(onLoginClick = { })
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
    RegisterScreen(onLoginClick = {})
}