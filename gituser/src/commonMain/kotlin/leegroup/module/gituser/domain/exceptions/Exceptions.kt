package leegroup.module.gituser.domain.exceptions

import leegroup.module.gituser.domain.models.Error

object NoConnectivityException : RuntimeException()
object ServerException : RuntimeException()

data class ApiException(
    val error: Error?,
    val httpCode: Int,
    val httpMessage: String?
) : RuntimeException()
