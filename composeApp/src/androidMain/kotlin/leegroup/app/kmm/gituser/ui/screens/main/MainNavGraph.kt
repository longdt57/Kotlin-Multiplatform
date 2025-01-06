package leegroup.app.kmm.gituser.ui.screens.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import leegroup.app.kmm.gituser.ui.base.BaseDestination
import leegroup.app.kmm.gituser.ui.composable
import leegroup.app.kmm.gituser.ui.navigateWithDestination
import leegroup.app.kmm.gituser.ui.AppDestination
import leegroup.app.kmm.gituser.ui.screens.main.gituser.GitUserListScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
) {

    navigation(
        route = AppDestination.MainNavGraph.route,
        startDestination = MainDestination.GitUserList.destination
    ) {
        composable(MainDestination.GitUserList) {
            GitUserListScreen(
                navigator = { destination -> navController.appNavigate(destination) }
            )
        }
//        composable<MainDestination.GitUserDetail.GitUserDetailLogin> { backStackEntry ->
//            val item = backStackEntry.toRoute<MainDestination.GitUserDetail.GitUserDetailLogin>()
//            GitUserDetailScreen(
//                login = item.login,
//                navigator = { destination ->
//                    navController.appNavigate(destination)
//                }
//            )
//        }
    }
}

fun NavHostController.appNavigate(destination: Any) {
    when (destination) {
        is BaseDestination -> navigateWithDestination(destination)
        else -> navigate(destination)
    }
}
