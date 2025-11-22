package ai.naver.carelink.data.repositories

import ai.naver.carelink.domain.model.User
import ai.naver.carelink.domain.repository.AuthRepository
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class AuthRepositoryImpl(private val auth: Auth) : AuthRepository {

    override suspend fun login(email: String, pass: String): Result<Unit> {
        return runCatching {
            auth.signInWith(Email) {
                this.email = email
                this.password = pass
            }
        }
    }

    override suspend fun register(email: String, pass: String, fullName: String): Result<Unit> {
        return runCatching {
            auth.signUpWith(Email) {
                this.email = email
                this.password = pass
                data = buildJsonObject {
                    put("full_name", fullName)
                }
            }
        }
    }

    override suspend fun logout() {
        auth.signOut()
    }

    override fun getCurrentUser(): User? {
        val supabaseUser = auth.currentUserOrNull() ?: return null
        return User(
            id = supabaseUser.id,
            email = supabaseUser.email,
            fullName = supabaseUser.userMetadata?.get("full_name")?.toString()?.replace("\"", "")
        )
    }
}
