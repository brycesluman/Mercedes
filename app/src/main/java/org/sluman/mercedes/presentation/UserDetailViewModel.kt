package org.sluman.mercedes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sluman.mercedes.data.DetailUiState
import org.sluman.mercedes.domain.UserUseCases
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val useCases: UserUseCases
): ViewModel(){
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getUserDetails(userId: String) {
        _uiState.value = uiState.value.copy(
            isError = false,
            isLoading = true
        )
        viewModelScope.launch {
            try {
                useCases.getUserDetailUseCase(userId).let { userDetail ->
                    _uiState.value = uiState.value.copy(
                        isError = false,
                        isLoading = false,
                        userDetail = userDetail
                    )
                }
            } catch (e: Exception) {
                println("error: $e")
                _uiState.value = uiState.value.copy(
                    isError = true,
                    errorMessage = e.toString(),
                    isLoading = false,
                )
            }

        }
    }
}