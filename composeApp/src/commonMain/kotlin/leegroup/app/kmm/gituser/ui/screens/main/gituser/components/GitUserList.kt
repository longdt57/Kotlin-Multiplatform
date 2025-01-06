package leegroup.app.kmm.gituser.ui.screens.main.gituser.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import leegroup.app.kmm.gituser.domain.models.GitUserModel
import leegroup.app.kmm.gituser.ui.components.LoadMore
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun GitUserList(
    modifier: Modifier = Modifier,
    users: List<GitUserModel>,
    onClick: (GitUserModel) -> Unit,
    onLinkClick: (String) -> Unit,
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
            GitUserListCard(user, onClick = onClick, onLinkClick = onLinkClick)
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    MaterialTheme {
        GitUserList(
            users = listOf(
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
            onLinkClick = {},
            onLoadMore = {}
        )
    }
}
