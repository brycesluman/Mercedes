package org.sluman.mercedes.data

data class DetailUiState(
    val isError: Boolean = false,
    val errorMessage: String? = "",
    val isLoading: Boolean = false,
    val userDetail: UserDetailEntity? = null
)
