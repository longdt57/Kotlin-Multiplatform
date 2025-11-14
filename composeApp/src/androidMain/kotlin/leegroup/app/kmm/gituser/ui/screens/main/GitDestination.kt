package leegroup.app.kmm.gituser.ui.screens.main

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

sealed class GitDestination {

    @Keep
    @Serializable
    object GitUserList
}
