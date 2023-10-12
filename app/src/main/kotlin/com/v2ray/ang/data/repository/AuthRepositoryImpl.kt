package com.v2ray.ang.data.repository

import com.apollographql.apollo3.ApolloClient
import com.v2ray.ang.LoginMutation
import com.v2ray.ang.SignupMutation
import com.v2ray.ang.util.AuthResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : AuthRepository {

    override suspend fun login(
        phone: String,
        password: String
    ): AuthResult<String> {
        val loginMutation = LoginMutation(phone = phone, password = password)
        val response = apolloClient.mutation(loginMutation).execute()

        val hasError = response.errors?.isNotEmpty() ?: false
        if (hasError) {
            return AuthResult.Error((response.errors?.get(0)?.message ?: "Unknown error"))
        }

        return AuthResult.Success(response.data?.login?.accessToken.toString())
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        phone: String,
        password: String
    ): AuthResult<String> {
        val registerMutation = SignupMutation(
            firstName = firstName,
            lastName = lastName,
            phone = phone,
            password = password
        )
        val response = apolloClient.mutation(registerMutation).execute()

        val hasError = response.errors?.isNotEmpty() ?: false
        if (hasError) {
            return AuthResult.Error((response.errors?.get(0)?.message ?: "Unknown error"))
        }

        return AuthResult.Success(response.data?.signup?.accessToken.toString())
    }
}