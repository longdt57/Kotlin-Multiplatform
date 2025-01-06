package leegroup.app.kmm.gituser.ui.screens.main.gituser.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import leegroup.app.kmm.gituser.UserAvatar
import leegroup.app.kmm.gituser.ui.components.AppHorizontalDivider
import leegroup.app.kmm.gituser.ui.components.LinkText
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun GitUserListItem(
    modifier: Modifier = Modifier,
    title: String,
    avatarUrl: String?,
    htmlUrl: String?,
    onLinkClick: (String) -> Unit
) {
    Row(modifier = modifier) {
        UserAvatar(modifier = Modifier.size(120.dp), avatarUrl = avatarUrl)
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            GitUserTitle(modifier = Modifier.fillMaxWidth(), title = title)
            AppHorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            htmlUrl?.let { LinkText(url = it, onClick = onLinkClick) }
        }
    }
}

@Composable
private fun GitUserTitle(modifier: Modifier, title: String) {
    Text(
        modifier = modifier,
        text = title,
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.SemiBold
    )
}

@Preview
@Composable
private fun GitUserContentPreview() {
    MaterialTheme {
        GitUserListItem(
            modifier = Modifier.padding(8.dp),
            title = "longdt57",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            htmlUrl = "https://github.com/longdt57",
            onLinkClick = {}
        )
    }
}
