package leegroup.module.gituser.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import leegroup.module.gituser.ui.screens.gituser.GitUserListScreen
import leegroup.module.gituser.ui.screens.gituserdetail.GitUserDetailScreen

fun NavGraphBuilder.gitNavGraph(
    navController: NavHostController,
) {

    navigation(
        route = GitUserDestination.GitUserRoot::class,
        startDestination = GitUserDestination.GitUserList
    ) {
        composable<GitUserDestination.GitUserList> {
            GitUserListScreen(
                navigator = { destination -> navController.navigate(destination) }
            )
        }

        composable<GitUserDestination.GitUserDetail> {
            GitUserDetailScreen(
                navHostController = navController,
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
                    .statusBarsPadding()
            )
        }
    }
}
