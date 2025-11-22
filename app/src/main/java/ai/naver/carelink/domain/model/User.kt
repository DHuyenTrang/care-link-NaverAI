package ai.naver.carelink.domain.model

data class User(
    val id: String,
    val email: String?,
    val fullName: String? = null
)
