package ai.naver.carelink.data.model.api_utils

data class Failure(
    val errorType: ErrorType? = null,
    override val message: String? = null,
    val errorCode: Int? = null,
) : Throwable()
