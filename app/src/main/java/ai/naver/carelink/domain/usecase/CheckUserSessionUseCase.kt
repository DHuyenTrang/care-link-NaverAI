package ai.naver.carelink.domain.usecase

import ai.naver.carelink.domain.repository.AuthRepository

class CheckUserSessionUseCase(private val repository: AuthRepository) {
    operator fun invoke(): Boolean {
        return repository.getCurrentUser() != null
    }
}