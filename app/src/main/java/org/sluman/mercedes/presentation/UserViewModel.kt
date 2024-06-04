package org.sluman.mercedes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sluman.mercedes.data.UiState
import org.sluman.mercedes.domain.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

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
                repository.getUsers().let { users ->
                    _uiState.value = uiState.value.copy(
                        isLoading = false,
                        isError = false,
                        users = users
                    )
                }
            } catch (e: Exception) {
                _uiState.value = uiState.value.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }

}