package org.sluman.mercedes.data.network

import org.sluman.mercedes.data.UserDetailEntity
import org.sluman.mercedes.data.UserEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UserEntity>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") userId: String): UserDetailEntity
}