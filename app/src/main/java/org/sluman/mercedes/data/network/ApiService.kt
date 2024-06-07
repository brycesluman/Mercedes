package org.sluman.mercedes.data.network

import org.sluman.mercedes.data.UserDetailsNetworkEntity
import org.sluman.mercedes.data.UserNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UserNetworkEntity>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") userId: String): UserDetailsNetworkEntity
}