package com.v2ray.ang.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.v2ray.ang.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _registerState: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState.Loading(isLoading = false))
    val registerState = _registerState.asStateFlow()

    fun register(
        phone: String,
        password: String
    ) {
        viewModelScope.launch {
            // TODO : authRepository.register()
        }
    }
}

sealed interface RegisterState {
    data class Loading(val isLoading: Boolean) : RegisterState
    data class Success(val accessToken: String) : RegisterState
    data class Error(val message: String) : RegisterState
}
