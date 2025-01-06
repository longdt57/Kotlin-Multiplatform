package leegroup.app.kmm.gituser.ui.screens.main.gituser.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import leegroup.app.kmm.gituser.ui.components.AppHorizontalDivider
import leegroup.app.kmm.gituser.ui.components.LinkText
import leegroup.app.kmm.gituser.ui.components.UserAvatar
import leegroup.app.kmm.gituser.ui.theme.ComposeTheme

@Composable
fun GitUserListItem(
    modifier: Modifier = Modifier,
    title: String,
    avatarUrl: String?,
    htmlUrl: String?,
) {
    Row(modifier = modifier) {
        UserAvatar(modifier = Modifier.size(120.dp), avatarUrl = avatarUrl)
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            GitUserTitle(modifier = Modifier.fillMaxWidth(), title = title)
            AppHorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            htmlUrl?.let { LinkText(url = it) }
        }
    }
}

@Composable
private fun GitUserTitle(modifier: Modifier, title: String) {
    Text(
        modifier = modifier,
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold
    )
}

@Preview(showBackground = true)
@Composable
private fun GitUserContentPreview() {
    ComposeTheme {
        GitUserListItem(
            modifier = Modifier.padding(8.dp),
            title = "longdt57",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            htmlUrl = "https://github.com/longdt57"
        )
    }
}
