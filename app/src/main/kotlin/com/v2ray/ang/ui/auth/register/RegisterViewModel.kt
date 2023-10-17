package com.v2ray.ang.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.v2ray.ang.data.repository.AuthRepository
import com.v2ray.ang.util.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _registerState: MutableStateFlow<RegisterState> =
        MutableStateFlow(RegisterState.Loading(isLoading = false))
    val registerState = _registerState.asStateFlow()

    fun register(
        firstName: String,
        lastName: String,
        phone: String,
        password: String,
        passwordVerify: String
    ) {

        if (validateRegister(
                password = password,
                passwordVerify = passwordVerify,
                firstName = firstName,
                lastName = lastName,
                phone = phone
            ).isEmpty()
        ) {
            viewModelScope.launch {
                when (val result = authRepository.register(firstName, lastName, phone, password)) {
                    is AuthResult.Loading -> {
                        _registerState.value = RegisterState.Loading(isLoading = false)
                    }

                    is AuthResult.Success -> {
                        _registerState.value = RegisterState.Success(result.accessToken)
                    }

                    is AuthResult.Error -> {
                        _registerState.value = RegisterState.Error(message = listOf(result.message))
                    }
                }
            }
        } else {
            _registerState.value = RegisterState.Error(
                validateRegister(
                    password = password,
                    passwordVerify = passwordVerify,
                    firstName = firstName,
                    lastName = lastName,
                    phone = phone
                )
            )
        }
    }

    private fun validateRegister(
        password: String,
        passwordVerify: String,
        firstName: String,
        lastName: String,
        phone: String
    ): List<String> {
        val errors = mutableListOf<String>()

        if (firstName.isEmpty()) {
            errors.add("First name is empty")
        }

        if (lastName.isEmpty()) {
            errors.add("Last name is empty")
        }

        if (phone.isEmpty()) {
            errors.add("Phone is empty")
        }

        if (password.isEmpty()) {
            errors.add("Password is empty")
        }

        if (passwordVerify.isEmpty()) {
            errors.add("Password verify is empty")
        }

        if (password != passwordVerify) {
            errors.add("Passwords do not match")
        }

        return errors
    }
}

sealed interface RegisterState {
    data class Loading(val isLoading: Boolean) : RegisterState
    data class Success(val accessToken: String) : RegisterState
    data class Error(val message: List<String>) : RegisterState
}
