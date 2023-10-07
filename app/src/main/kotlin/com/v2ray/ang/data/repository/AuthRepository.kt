package com.v2ray.ang.data.repository

import com.v2ray.ang.util.AuthResult

interface AuthRepository {
    suspend fun login(phone: String, password: String): AuthResult<String>
    suspend fun register(phone: String, password: String)
}