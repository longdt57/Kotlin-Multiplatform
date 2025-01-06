package leegroup.app.kmm.gituser.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import leegroup.app.kmm.gituser.domain.models.GitUserModel

@Serializable
data class GitUser(

    @SerialName("id")
    val id: Long,

    @SerialName("login")
    val login: String,

    @SerialName("avatar_url")
    val avatarUrl: String?,

    @SerialName("html_url")
    val htmlUrl: String?,
)

fun GitUser.mapToDomain() = GitUserModel(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
)

fun List<GitUser>.mapToDomain() = map { it.mapToDomain() }
