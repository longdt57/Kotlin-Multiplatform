package leegroup.app.kmm.gituser.ui.screens.main.gituser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import leegroup.app.kmm.gituser.support.extensions.collectAsEffect
import leegroup.app.kmm.gituser.support.extensions.formatAndOpenUrl
import leegroup.app.kmm.gituser.ui.base.BaseScreen
import leegroup.app.kmm.gituser.ui.screens.main.gituser.components.GitUserListScreenContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun GitUserListScreen(
    viewModel: GitUserListViewModel = koinViewModel(),
    navigator: (destination: Any) -> Unit,
) {

    viewModel.navigator.collectAsEffect { destination -> navigator(destination) }

    LaunchedEffect(Unit) {
        viewModel.handleAction(GitUserListAction.LoadIfEmpty)
    }

    val context = LocalContext.current

    GitUserListScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding()
            .statusBarsPadding(),
        viewModel = viewModel,
        onClick = { user ->
        //            navigator(MainDestination.GitUserDetail.GitUserDetailLogin(user.login))
                },
        onLinkClick = {
            context.formatAndOpenUrl(it)
        }
    )
}
