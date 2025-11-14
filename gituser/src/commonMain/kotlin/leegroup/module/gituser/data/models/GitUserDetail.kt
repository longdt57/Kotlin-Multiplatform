package leegroup.module.gituser.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import leegroup.module.core.extensions.orZero
import leegroup.module.gituser.domain.models.GitUserDetailModel

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

internal fun GitUserDetail.mapToDomain() = GitUserDetailModel(
    id = id,
    login = login,
    name = name,
    avatarUrl = avatarUrl,
    blog = blog,
    location = location,
    followers = followers.orZero(),
    following = following.orZero(),
)
