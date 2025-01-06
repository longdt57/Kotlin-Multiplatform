package leegroup.app.kmm.gituser.ui.screens.main.gituser

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import leegroup.app.kmm.gituser.R
import leegroup.app.kmm.gituser.domain.models.GitUserModel
import leegroup.app.kmm.gituser.support.extensions.collectAsEffect
import leegroup.app.kmm.gituser.ui.base.BaseScreen
import leegroup.app.kmm.gituser.ui.base.LoadingState
import leegroup.app.kmm.gituser.ui.components.LoadMore
import leegroup.app.kmm.gituser.ui.components.UserCard
import leegroup.app.kmm.gituser.ui.screens.main.MainDestination
import leegroup.app.kmm.gituser.ui.screens.main.gituser.components.GitUserListEmpty
import leegroup.app.kmm.gituser.ui.screens.main.gituser.components.GitUserListItem
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GitUserListAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = R.string.git_user_list_screen_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
        })
}

@Composable
private fun GitUserList(
    modifier: Modifier = Modifier,
    users: ImmutableList<GitUserModel>,
    onClick: (GitUserModel) -> Unit,
    onLoadMore: () -> Unit
) {
    val listState = rememberLazyListState()
    LoadMore(listState = listState, onLoadMore = onLoadMore)
    LazyColumn(
        modifier = modifier,
        state = listState,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(users) { user ->
            GitUserListCard(user, onClick)
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun GitUserListCard(user: GitUserModel, onClick: (GitUserModel) -> Unit) {
    UserCard(modifier = Modifier.clickable { onClick(user) }) {
        GitUserListItem(
            modifier = Modifier.padding(12.dp),
            title = user.login,
            avatarUrl = user.avatarUrl,
            htmlUrl = user.htmlUrl
        )
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
