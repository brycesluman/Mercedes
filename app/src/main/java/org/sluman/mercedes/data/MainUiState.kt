package org.sluman.mercedes.data


data class MainUiState(
    val isError: Boolean = false,
    val errorMessage: String? = "",
    val isLoading: Boolean = false,
    val users: List<UserEntity>? = null
)
