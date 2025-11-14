package leegroup.module.gituser.ui.navigation

import kotlinx.serialization.Serializable

sealed class GitDestination {

    @Serializable
    object GitNavGraph

    @Serializable
    object GitUserList
}
