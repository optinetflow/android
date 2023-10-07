package com.v2ray.ang.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.v2ray.ang.AngApplication
import com.v2ray.ang.data.repository.AuthRepository
import com.v2ray.ang.util.AuthResult
import com.v2ray.ang.util.Prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Loading(isLoading = false))
    val loginState = _loginState.asStateFlow()

    fun login(phone: String, password: String) {
        _loginState.value = LoginState.Loading(isLoading = true)

        viewModelScope.launch {
            when (val result = authRepository.login(phone, password)) {
                is AuthResult.Loading -> _loginState.value = LoginState.Loading(isLoading = true)
                is AuthResult.Success -> {
                    saveToken(result.accessToken)
                    _loginState.value = LoginState.Success(result.accessToken)
                }

                is AuthResult.Error -> _loginState.value = LoginState.Error(result.message)
            }
        }
    }

    private fun saveToken(accessToken: String) {
        val prefs: Prefs by lazy {
            Prefs(AngApplication.instance)
        }

        prefs.myString = accessToken
    }
}

sealed interface LoginState {
    data class Loading(val isLoading: Boolean) : LoginState
    data class Success(val accessToken: String) : LoginState
    data class Error(val message: String) : LoginState
}