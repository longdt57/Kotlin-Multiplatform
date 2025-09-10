package leegroup.module.designsystem.ui.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class ErrorModel(
    @SerialName("message")
    val message: String? = null,
    @SerialName("code")
    val code: Int? = null,
) {
    companion object {
        val UnknownErrorModel = ErrorModel(
            message = "Unknown error",
            code = 0,
        )
    }
}