package com.v2ray.ang.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.v2ray.ang.AngApplication
import com.v2ray.ang.data.repository.AuthRepository
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

    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Loading)
    val loginState = _loginState.asStateFlow()

    fun login(phone: String, password: String) {
        viewModelScope.launch {
            val response = authRepository.login(phone, password)
            if (response.isNotEmpty()) {
                _loginState.value = LoginState.Success(response)
            } else {
                _loginState.value = LoginState.Error(response)
            }
        }
    }

    fun saveToken(accessToken: String) {
        val prefs: Prefs by lazy {
            Prefs(AngApplication.instance)
        }

        prefs.myString = accessToken
    }

}

sealed interface LoginState {
    data object Loading : LoginState
    data class Success(val accessToken: String) : LoginState
    data class Error(val message: String) : LoginState
}