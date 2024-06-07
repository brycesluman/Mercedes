package org.sluman.mercedes.domain

interface UserRepository {
    suspend fun getUsers(): List<UserDomainEntity>

    suspend fun getUserDetails(userId: String): UserDetailsDomainEntity
}