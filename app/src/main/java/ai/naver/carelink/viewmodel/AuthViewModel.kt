package ai.naver.carelink.viewmodel

import ai.naver.carelink.domain.usecase.CheckUserSessionUseCase
import ai.naver.carelink.domain.usecase.LoginUseCase
import ai.naver.carelink.domain.usecase.RegisterUseCase
import ai.naver.carelink.ui.auth.AuthUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val checkUserSessionUseCase: CheckUserSessionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun isUserLoggedIn(): Boolean = checkUserSessionUseCase()

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            loginUseCase(email, pass)
                .onSuccess {
                    _uiState.value = AuthUiState.Success("Đăng nhập thành công")
                }
                .onFailure { error ->
                    _uiState.value = AuthUiState.Error(error.message ?: "Lỗi đăng nhập")
                }
        }
    }

    fun register(email: String, pass: String, fullName: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            registerUseCase(email, pass, fullName)
                .onSuccess {
                    _uiState.value = AuthUiState.Success("Đăng ký thành công!")
                }
                .onFailure { error ->
                    _uiState.value = AuthUiState.Error(error.message ?: "Lỗi đăng ký")
                }
        }
    }

    fun resetState() {
        _uiState.value = AuthUiState.Idle
    }
}
