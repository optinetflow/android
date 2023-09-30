package com.v2ray.ang.data.repository

interface AuthRepository {
    suspend fun login(phone: String, password: String): String
    suspend fun register(phone: String, password: String)
}