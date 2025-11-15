package leegroup.module.gituser.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import leegroup.module.core.extensions.orZero
import leegroup.module.gituser.domain.models.GitUserDetailModel

@Entity
@Serializable
internal data class GitUserDetail(
    @SerialName("id")
    @PrimaryKey
    val id: Long,

    @SerialName("login")
    @ColumnInfo(name = "login")
    val login: String,

    @SerialName("name")
    @ColumnInfo(name = "name")
    val name: String?,

    @SerialName("avatar_url")
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String?,

    @SerialName("blog")
    @ColumnInfo(name = "blog")
    val blog: String?,

    @SerialName("location")
    @ColumnInfo(name = "location")
    val location: String?,

    @SerialName("followers")
    @ColumnInfo(name = "followers")
    val followers: Int?,

    @SerialName("following")
    @ColumnInfo(name = "following")
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
