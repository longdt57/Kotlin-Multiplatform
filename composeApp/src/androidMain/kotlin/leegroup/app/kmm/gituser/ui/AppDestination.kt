package leegroup.app.kmm.gituser.ui

import kotlinx.serialization.Serializable

sealed class AppDestination {

    @Serializable
    object RootNavGraph

    @Serializable
    object MainNavGraph
}
