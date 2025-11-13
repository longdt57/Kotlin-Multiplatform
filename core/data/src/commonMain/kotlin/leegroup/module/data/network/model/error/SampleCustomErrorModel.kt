package leegroup.module.data.network.model.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Just for testing custom error model parsing.
 */
@Serializable
internal data class SampleCustomErrorModel(
    @SerialName("code")
    val code: Int,

    @SerialName("message")
    override val message: String,

    @SerialName("title")
    val title: String,
) : Throwable(message)
