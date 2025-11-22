package ai.naver.carelink.domain.usecase

import ai.naver.carelink.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, pass: String): Result<Unit> {
        if (email.isBlank() || pass.isBlank()) {
            return Result.failure(IllegalArgumentException("Email và mật khẩu không được để trống"))
        }
        return repository.login(email, pass)
    }
}