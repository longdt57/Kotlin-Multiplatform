package leegroup.module.gituser.ui.screens.gituser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import leegroup.module.core.extensions.compose.collectAsEffect
import leegroup.module.designsystem.getPlatform
import leegroup.module.gituser.ui.screens.gituser.components.GitUserListScreenContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GitUserListScreen(
    navigator: (destination: Any) -> Unit,
    viewModel: GitUserListViewModel = koinViewModel()
) {

    viewModel.navigator.collectAsEffect { destination -> navigator(destination) }

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
            getPlatform().openUrl(it)
        }
    )
}
