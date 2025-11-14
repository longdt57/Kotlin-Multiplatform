package leegroup.app.kmm.gituser.ui.screens.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import leegroup.app.kmm.gituser.ui.AppDestination
import leegroup.app.kmm.gituser.ui.screens.main.gituser.GitUserListScreen

fun NavGraphBuilder.gitNavGraph(
    navController: NavHostController,
) {

    navigation(
        route = AppDestination.MainNavGraph::class,
        startDestination = GitDestination.GitUserList
    ) {
        composable<GitDestination.GitUserList> {
            GitUserListScreen(
                navigator = { destination -> navController.navigate(destination) }
            )
        }
    }
}
