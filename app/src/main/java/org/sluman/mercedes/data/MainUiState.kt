package org.sluman.mercedes.data

import org.sluman.mercedes.domain.UserDomainEntity


data class MainUiState(
    val isError: Boolean = false,
    val errorMessage: String? = "",
    val isLoading: Boolean = false,
    val users: List<UserDomainEntity>? = null
)
