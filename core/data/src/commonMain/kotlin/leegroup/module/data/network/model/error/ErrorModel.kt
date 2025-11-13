package leegroup.module.data.network.model.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorModel(
    @SerialName("code") val code: Int? = null,
    @SerialName("message") override val message: String? = null,
) : Throwable(message)
