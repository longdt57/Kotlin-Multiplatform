package leegroup.app.kmm.gituser.domain.exceptions

import leegroup.app.kmm.gituser.domain.models.Error

object NoConnectivityException : RuntimeException()
object ServerException : RuntimeException()

data class ApiException(
    val error: Error?,
    val httpCode: Int,
    val httpMessage: String?
) : RuntimeException()
