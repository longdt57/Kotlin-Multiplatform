package leegroup.app.kmm.gituser.ui.screens.main.gituser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import leegroup.app.kmm.gituser.domain.models.GitUserModel
import leegroup.app.kmm.gituser.support.extensions.collectAsEffect
import leegroup.app.kmm.gituser.support.extensions.formatAndOpenUrl
import leegroup.app.kmm.gituser.ui.base.BaseScreen
import leegroup.app.kmm.gituser.ui.base.LoadingState
import leegroup.app.kmm.gituser.ui.screens.main.gituser.components.GitUserList
import leegroup.app.kmm.gituser.ui.screens.main.gituser.components.GitUserListAppBar
import leegroup.app.kmm.gituser.ui.screens.main.gituser.components.GitUserListEmpty
import leegroup.app.kmm.gituser.ui.theme.ComposeTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun GitUserListScreen(
    viewModel: GitUserListViewModel = koinViewModel(),
    navigator: (destination: Any) -> Unit,
) = BaseScreen(viewModel) {
    viewModel.navigator.collectAsEffect { destination -> navigator(destination) }
    val uiModel by viewModel.uiModel.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    val isLoading by remember {
        derivedStateOf {
            loading is LoadingState.Loading
        }
    }

    LaunchedEffect(Unit) {
        viewModel.handleAction(GitUserListAction.LoadIfEmpty)
    }

    GitUserListScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding()
            .statusBarsPadding(),
        showRefresh = uiModel.users.isEmpty() && isLoading.not(),
        users = uiModel.users,
        onLoadMore = {
            viewModel.handleAction(GitUserListAction.LoadMore)
        },
        onClick = { user ->
//            navigator(MainDestination.GitUserDetail.GitUserDetailLogin(user.login))
        },
        onRefresh = {
            viewModel.handleAction(GitUserListAction.LoadIfEmpty)
        }
    )
}

@Composable
private fun GitUserListScreenContent(
    modifier: Modifier = Modifier,
    showRefresh: Boolean,
    users: ImmutableList<GitUserModel>,
    onClick: (GitUserModel) -> Unit,
    onLoadMore: () -> Unit,
    onRefresh: () -> Unit,
) {
    val context = LocalContext.current
    Column(modifier = modifier) {
        GitUserListAppBar()
        if (users.isNotEmpty()) {
            GitUserList(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                users = users,
                onClick = onClick,
                onLinkClick = {
                    context.formatAndOpenUrl(it)
                },
                onLoadMore = onLoadMore
            )
        } else if (showRefresh) {
            GitUserListEmpty(
                modifier = Modifier.fillMaxSize(),
                onRefresh = onRefresh
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ScreenEmptyPreview() {
    ComposeTheme {
        GitUserListScreenContent(
            showRefresh = true,
            users = persistentListOf(),
            onClick = {},
            onLoadMore = {},
            onRefresh = {}
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ScreenPreview() {
    ComposeTheme {
        GitUserListScreenContent(
            showRefresh = false,
            users = persistentListOf(
                GitUserModel(
                    id = 1,
                    login = "longdt57",
                    avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                    htmlUrl = "https://github.com/longdt57"
                ),
                GitUserModel(
                    id = 2,
                    login = "defunkt",
                    avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
                    htmlUrl = "https://github.com/defunkt"
                ),
                GitUserModel(
                    id = 3,
                    login = "pjhyett",
                    avatarUrl = "https://avatars.githubusercontent.com/u/3?v=4",
                    htmlUrl = "https://github.com/pjhyett"
                )
            ),
            onClick = {},
            onLoadMore = {},
            onRefresh = {}
        )
    }
}
