package leegroup.app.kmm.gituser.ui.screens.main.gituser.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import leegroup.app.kmm.gituser.domain.models.GitUserModel
import leegroup.app.kmm.gituser.ui.components.UserCard
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun GitUserListCard(
    user: GitUserModel,
    onClick: (GitUserModel) -> Unit,
    onLinkClick: (String) -> Unit
) {
    UserCard(modifier = Modifier.clickable { onClick(user) }) {
        GitUserListItem(
            modifier = Modifier.padding(12.dp),
            title = user.login,
            avatarUrl = user.avatarUrl,
            htmlUrl = user.htmlUrl,
            onLinkClick = onLinkClick,
        )
    }
}

@Preview()
@Composable
private fun ScreenPreview() {
    MaterialTheme {
        GitUserListCard(
            user = GitUserModel(
                id = 1,
                login = "longdt57",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                htmlUrl = "https://github.com/longdt57"
            ),
            onClick = {},
            onLinkClick = {},
        )
    }
}
