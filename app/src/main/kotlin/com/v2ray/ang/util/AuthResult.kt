package com.v2ray.ang.util

sealed class AuthResult<out T> {
    data object Loading : AuthResult<Nothing>()
    data class Success<T>(val accessToken: T) : AuthResult<T>()
    data class Error(val message: String) : AuthResult<Nothing>()
}