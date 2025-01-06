package leegroup.app.kmm.gituser.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GitUserDetail(
    @SerialName("id")
    val id: Long,

    @SerialName("login")
    val login: String,

    @SerialName("name")
    val name: String?,

    @SerialName("avatar_url")
    val avatarUrl: String?,

    @SerialName("blog")
    val blog: String?,

    @SerialName("location")
    val location: String?,

    @SerialName("followers")
    val followers: Int?,

    @SerialName("following")
    val following: Int?
)
