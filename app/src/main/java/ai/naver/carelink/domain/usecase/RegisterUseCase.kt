package ai.naver.carelink.domain.usecase

import ai.naver.carelink.domain.repository.AuthRepository

class RegisterUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, pass: String, fullName: String): Result<Unit> {
        if (fullName.isBlank()) {
            return Result.failure(IllegalArgumentException("Họ tên không được để trống"))
        }
        return repository.register(email, pass, fullName)
    }
}