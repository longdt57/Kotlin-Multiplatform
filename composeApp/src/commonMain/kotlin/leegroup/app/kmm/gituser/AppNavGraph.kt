package leegroup.app.kmm.gituser

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import leegroup.module.designsystem.theme.ComposeTheme
import leegroup.module.gituser.ui.navigation.GitUserDestination
import leegroup.module.gituser.ui.navigation.gitNavGraph

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    ComposeTheme {
        NavHost(
            navController = navController,
            route = AppDestination.RootNavGraph::class,
            startDestination = GitUserDestination.GitUserRoot
        ) {
            gitNavGraph(navController = navController)
        }
    }
}
