package leegroup.app.kmm.gituser.domain.models

data class GitUserDetailModel(
    val id: Long,
    val login: String,
    val name: String?,
    val avatarUrl: String?,
    val blog: String?,
    val location: String?,
    val followers: Int,
    val following: Int,
)