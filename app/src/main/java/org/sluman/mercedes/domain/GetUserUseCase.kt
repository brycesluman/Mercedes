package org.sluman.mercedes.domain

class GetUsersUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): List<UserDomainEntity> {
        return repository.getUsers()
    }
}