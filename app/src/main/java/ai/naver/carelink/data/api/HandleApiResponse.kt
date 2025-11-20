package ai.naver.carelink.data.api

import ai.naver.carelink.data.model.api_utils.ApiResponse
import ai.naver.carelink.data.model.api_utils.Either
import ai.naver.carelink.data.model.api_utils.ErrorType
import ai.naver.carelink.data.model.api_utils.Failure
import ai.naver.carelink.data.model.api_utils.toGofaApiResponseFail
import retrofit2.Response
import ai.naver.carelink.service.NetworkService

object HandleApiResponse {
    fun whenInternetError(networkService: NetworkService): Either<Failure, Nothing>? {
        if (!networkService.isConnected()) {
            return Either.failure(
                Failure(
                    ErrorType.LOST_INTERNET,
                    "No internet connection"
                )
            )
        }
        return null
    }

    fun <R> processResponseData(response: Response<ApiResponse<R>>): Either<Failure, R> {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                if (body.success == true) {
                    return Either.success(body.data) as Either<Failure, R>
                }

                return Either.failure(
                    Failure(
                        ErrorType.SERVER_RESPONSE_ERROR,
                        body.message
                    )
                )
            }
        }

        val responseFail = response.toGofaApiResponseFail()
        return Either.failure(
            Failure(
                ErrorType.SERVER_RESPONSE_ERROR,
                responseFail?.message,
                responseFail?.code
            )
        )
    }
}

annotation class NetworkService
