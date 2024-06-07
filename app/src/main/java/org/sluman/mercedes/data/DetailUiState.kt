package org.sluman.mercedes.data

import org.sluman.mercedes.domain.UserDetailsDomainEntity

data class DetailUiState(
    val isError: Boolean = false,
    val errorMessage: String? = "",
    val isLoading: Boolean = false,
    val userDetail: UserDetailsDomainEntity? = null
)
