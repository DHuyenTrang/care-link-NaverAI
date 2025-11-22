package ai.naver.carelink.domain.repository

import ai.naver.carelink.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, pass: String): Result<Unit>
    suspend fun register(email: String, pass: String, fullName: String): Result<Unit>
    suspend fun logout()
    fun getCurrentUser(): User?
}