package org.sluman.mercedes.domain

class GetUserDetailsUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userId: String): UserDetailsDomainEntity {
        return repository.getUserDetails(userId)
    }

}