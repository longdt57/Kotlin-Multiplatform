package leegroup.app.kmm.gituser.ui

import leegroup.app.kmm.gituser.ui.base.BaseDestination

sealed class AppDestination {

    object RootNavGraph : BaseDestination("rootNavGraph")

    object MainNavGraph : BaseDestination("mainNavGraph")
}
