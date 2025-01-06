package leegroup.app.kmm.gituser.ui.screens.main

import kotlinx.serialization.Serializable
import leegroup.app.kmm.gituser.ui.base.BaseDestination

sealed class MainDestination {
    object GitUserList : BaseDestination("gitUserList")
    object GitUserDetail : BaseDestination("gitUserDetail/{login}") {

        // New composable: https://developer.android.com/develop/ui/compose/navigation#deeplinks
        @Serializable
        data class GitUserDetailLogin(val login: String)
    }
}
