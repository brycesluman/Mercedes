package org.sluman.mercedes.data


data class UiState(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val users: List<UserEntity>? = null,
    val userDetail: UserDetailEntity? = null
)
