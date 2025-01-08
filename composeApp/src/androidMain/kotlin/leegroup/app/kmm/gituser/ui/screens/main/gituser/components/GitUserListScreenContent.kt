package leegroup.app.kmm.gituser.ui.screens.main.gituser.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import leegroup.app.kmm.gituser.GitUserListAppBar
import leegroup.app.kmm.gituser.domain.models.GitUserModel
import leegroup.app.kmm.gituser.ui.base.ErrorView
import leegroup.app.kmm.gituser.ui.base.LoadingState
import leegroup.app.kmm.gituser.ui.base.LoadingView
import leegroup.app.kmm.gituser.ui.screens.main.gituser.GitUserListAction
import leegroup.app.kmm.gituser.ui.screens.main.gituser.GitUserListViewModel

@Composable
fun GitUserListScreenContent(
    modifier: Modifier = Modifier,
    viewModel: GitUserListViewModel,
    onClick: (GitUserModel) -> Unit,
    onLinkClick: (String) -> Unit,
) {
    val uiModel by viewModel.uiModel.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    val isLoading by remember {
        derivedStateOf {
            loading is LoadingState.Loading
        }
    }

    Column(modifier = modifier) {
        GitUserListAppBar()
        if (uiModel.users.isNotEmpty()) {
            GitUserList(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                users = uiModel.users,
                onClick = onClick,
                onLinkClick = onLinkClick,
                onLoadMore = { viewModel.handleAction(GitUserListAction.LoadMore) }
            )
        } else if (isLoading.not()) {
            GitUserListEmpty(
                modifier = Modifier.fillMaxSize(),
                onRefresh = {
                    viewModel.handleAction(GitUserListAction.LoadIfEmpty)
                }
            )
        }
    }
    LoadingView(viewModel)
    ErrorView(viewModel)
}
