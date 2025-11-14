package leegroup.module.gituser.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import leegroup.module.gituser.ui.screens.gituser.GitUserListScreen

fun NavGraphBuilder.gitNavGraph(
    navController: NavHostController,
) {

    navigation(
        route = GitDestination.GitNavGraph::class,
        startDestination = GitDestination.GitUserList
    ) {
        composable<GitDestination.GitUserList> {
            GitUserListScreen(
                navigator = { destination -> navController.navigate(destination) }
            )
        }
    }
}
