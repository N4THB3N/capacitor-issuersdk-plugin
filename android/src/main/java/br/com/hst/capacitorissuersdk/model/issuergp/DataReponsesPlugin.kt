package br.com.hst.capacitorissuersdk.model.issuergp

import br.com.hst.issuergp.data.model.*

data class DefaultResponse<T>(
    val result: ResultStatus,
    val data: T
)

class DefaultErrorResponse(
    val statusCode: IssuerGpStatusCode,
    val message: String
)

class IssuerGpInitializer

data class IsAvailablePlugin(val isAvailable: Boolean)

data class TokensPlugin(val tokens: List<GooglePayToken>)

data class CTokensPlugin(val containsToken: Boolean)

enum class ResultStatus {
    SUCCESS, FAILURE
}