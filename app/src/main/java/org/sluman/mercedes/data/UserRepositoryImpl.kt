package org.sluman.mercedes.data

import org.sluman.mercedes.data.network.ApiClient
import org.sluman.mercedes.domain.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient
): UserRepository {
    override suspend fun getUsers(): List<UserEntity>  {
        return apiClient.apiService.getUsers()
    }

    override suspend fun getUserDetails(userId: String): UserDetailEntity {
        return apiClient.apiService.getUserById(userId)
    }
}