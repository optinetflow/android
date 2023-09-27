package com.v2ray.ang.data.repository

import com.apollographql.apollo3.ApolloClient
import com.v2ray.ang.LoginMutation
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : AuthRepository {

    override suspend fun login(
        phone: String,
        password: String
    ): String {
        try {
            val loginMutation = LoginMutation(phone = phone, password = password)
            val response = apolloClient.mutation(loginMutation).execute()

            val hasError = response.hasErrors()
            if (hasError) {
                return response.errors?.get(0)?.message.toString()
            }

            val accessToken = response.data?.login?.accessToken
            return accessToken.toString()
        } catch (e: Exception) {
            return e.message.toString()
        }
    }
}