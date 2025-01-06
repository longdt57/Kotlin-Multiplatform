package leegroup.app.kmm.gituser.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import leegroup.app.kmm.gituser.domain.models.Error

@Serializable
internal data class ErrorResponse(
    @SerialName("message")
    val message: String,
)

internal fun ErrorResponse.mapToError() = Error(
    message = message
)
