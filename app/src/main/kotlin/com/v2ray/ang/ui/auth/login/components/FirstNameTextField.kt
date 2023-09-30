package com.v2ray.ang.ui.auth.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.v2ray.ang.R

@Composable
fun FirstNameTextField(
    modifier: Modifier = Modifier,
    firstName: (String) -> Unit
) {
    val name = rememberSaveable { mutableStateOf("") }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        value = name.value,
        onValueChange = {
            name.value = it
            firstName(name.value)
        },
        label = { Label() },
        textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.iran_sans))),
        singleLine = true,
        leadingIcon = { LeadingIcon() },
        shape = RoundedCornerShape(30.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
private fun Label() {
    Text(
        text = stringResource(R.string.name),
        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.iran_sans))
        )
    )
}

@Composable
private fun LeadingIcon() {
    Image(
        painter = painterResource(R.drawable.baseline_person_24),
        contentDescription = null
    )
}

@Composable
@Preview(showBackground = true, locale = "fa")
private fun FirstNameTextFieldPreview() {
    FirstNameTextField(firstName = {})
}