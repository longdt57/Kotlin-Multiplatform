package leegroup.module.data.network

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import leegroup.module.data.network.model.error.ErrorModel

/**
 * Use object to add to kover
 */
object ErrorMapper {
    suspend fun Throwable.mapApiError(): Throwable {
        return mapApiCustomError<ErrorModel>()
    }

    @Suppress("TooGenericExceptionCaught")
    suspend inline fun <reified Model : Throwable> Throwable.mapApiCustomError(): Throwable {
        return try {
            when (this) {
                is ClientRequestException -> response.body<Model>()
                else -> this
            }
        } catch (e: Exception) {
            e
        }
    }
}
