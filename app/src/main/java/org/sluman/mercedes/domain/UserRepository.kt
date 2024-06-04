package org.sluman.mercedes.domain

import org.sluman.mercedes.data.UserDetailEntity
import org.sluman.mercedes.data.UserEntity

interface UserRepository {
    suspend fun getUsers(): List<UserEntity>

    suspend fun getUserDetails(userId: String): UserDetailEntity
}