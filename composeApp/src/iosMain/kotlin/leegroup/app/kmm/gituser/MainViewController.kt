package leegroup.app.kmm.gituser

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import leegroup.app.kmm.gituser.domain.models.GitUserModel
import leegroup.app.kmm.gituser.ui.screens.main.gituser.components.GitUserList
import leegroup.app.kmm.gituser.ui.screens.main.gituser.components.GitUserListEmpty

fun MainViewController() = ComposeUIViewController { App() }

fun GitUserListEmptyController(onRefresh: () -> Unit) = ComposeUIViewController {
    GitUserListEmpty(modifier = Modifier.fillMaxWidth(), onRefresh = onRefresh)
}

fun GitUserListController(
    users: List<GitUserModel>,
    onClick: (GitUserModel) -> Unit,
    onLinkClick: (String) -> Unit,
    onLoadMore: () -> Unit
) = ComposeUIViewController {
    GitUserList(
        modifier = Modifier.fillMaxWidth(),
        users = users,
        onClick = onClick,
        onLinkClick = onLinkClick,
        onLoadMore = onLoadMore,
    )
}