package org.sluman.mercedes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sluman.mercedes.data.MainUiState
import org.sluman.mercedes.domain.UserUseCases
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val useCases: UserUseCases
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        getUserList()
    }

    private fun getUserList() {
        _uiState.value = uiState.value.copy(
            isLoading = true,
            isError = false
        )
        viewModelScope.launch {
            try {
                useCases.getUsersUseCase().let { users ->
                    _uiState.value = uiState.value.copy(
                        isLoading = false,
                        isError = false,
                        users = users
                    )
                }
            } catch (e: Exception) {
                println("error: $e")
                _uiState.value = uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.toString(),
                    isError = true
                )
            }
        }
    }

}