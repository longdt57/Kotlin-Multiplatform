package leegroup.module.gituser.ui.navigation

import kotlinx.serialization.Serializable

sealed interface GitUserDestination {

    @Serializable
    object GitUserRoot: GitUserDestination

    @Serializable
    object GitUserList: GitUserDestination

    @Serializable
    data class GitUserDetail(
        val login: String
    ) : GitUserDestination
}
