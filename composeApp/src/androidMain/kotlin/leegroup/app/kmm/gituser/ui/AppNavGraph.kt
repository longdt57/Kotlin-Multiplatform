package leegroup.app.kmm.gituser.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import leegroup.app.kmm.gituser.ui.screens.main.gitNavGraph

@Composable
fun AppNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = AppDestination.RootNavGraph::class,
        startDestination = AppDestination.MainNavGraph
    ) {
        gitNavGraph(navController = navController)
    }
}
