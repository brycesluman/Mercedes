package org.sluman.mercedes.data

import org.sluman.mercedes.data.network.ApiClient
import org.sluman.mercedes.domain.UserDetailsDomainEntity
import org.sluman.mercedes.domain.UserDomainEntity
import org.sluman.mercedes.domain.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient
) : UserRepository {
    override suspend fun getUsers(): List<UserDomainEntity> {
        return apiClient.apiService.getUsers().map { networkEntity ->
            networkEntity.toDomain()
        }
    }

    override suspend fun getUserDetails(userId: String): UserDetailsDomainEntity {
        return apiClient.apiService.getUserById(userId).toDomain()
    }
}