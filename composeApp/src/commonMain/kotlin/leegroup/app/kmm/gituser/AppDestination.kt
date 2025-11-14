package leegroup.app.kmm.gituser

import kotlinx.serialization.Serializable

sealed class AppDestination {

    @Serializable
    object RootNavGraph
}
