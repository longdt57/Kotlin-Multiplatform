package leegroup.module.gituser.data.extensions

import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException
import leegroup.module.gituser.data.remote.responses.ErrorResponse
import leegroup.module.gituser.data.remote.responses.mapToError
import leegroup.module.gituser.data.util.JsonUtil
import leegroup.module.gituser.domain.exceptions.ApiException
import leegroup.module.gituser.domain.exceptions.NoConnectivityException
import leegroup.module.gituser.domain.exceptions.ServerException
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
internal fun <T> flowTransform(@BuilderInference block: suspend FlowCollector<T>.() -> T) = flow {
    runCatching { block() }
        .onSuccess { result -> emit(result) }
        .onFailure { exception -> throw exception.mapError() }
}

internal suspend fun <T> transform(block: suspend () -> T): T {
    return runCatching {
        block()
    }.getOrElse { exception ->
        throw exception.mapError()
    }
}

private suspend fun Throwable.mapError(): Throwable {
    return when (this) {
        is UnresolvedAddressException, is IOException, is SocketTimeoutException -> NoConnectivityException
        is SerializationException -> ServerException

        is ResponseException -> {
            val errorResponse = parseErrorResponse(this.response.bodyAsText())
            ApiException(
                errorResponse?.mapToError(),
                this.response.status.value,
                this.message
            )
        }

        else -> this
    }
}

private fun parseErrorResponse(response: String): ErrorResponse? {
    return try {
        JsonUtil.decodeFromString(response)
    } catch (e: Exception) {
        null
    }
}
