package leegroup.module.gituser.ui.screens.gituserdetail.components

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
import androidx.compose.ui.unit.dp
import leegroup.module.designsystem.theme.ComposeTheme
import leegroup.module.gituser.ui.components.AppHorizontalDivider
import leegroup.module.gituser.ui.components.UserAvatar
import leegroup.module.gituser.ui.components.UserCard
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun GitUserDetailCard(
    modifier: Modifier = Modifier,
    name: String,
    avatarUrl: String,
    location: String,
) {
    UserCard(modifier = modifier) {
        GitUserDetailItem(
            modifier = Modifier.padding(12.dp),
            name = name,
            avatarUrl = avatarUrl,
            location = location
        )
    }
}

@Composable
fun GitUserDetailItem(
    modifier: Modifier = Modifier,
    name: String,
    avatarUrl: String,
    location: String,
) {
    Row(modifier = modifier) {
        UserAvatar(modifier = Modifier.size(120.dp), avatarUrl = avatarUrl)
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            GitUserDetailTitle(modifier = Modifier.fillMaxWidth(), title = name)
            AppHorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.5.dp
            )
            GitUserDetailLocation(modifier = Modifier.fillMaxWidth(), location = location)
        }
    }
}

@Composable
private fun GitUserDetailTitle(modifier: Modifier, title: String) {
    Text(
        modifier = modifier,
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold
    )
}

@Preview(showBackground = true)
@Composable
private fun GitUserDetailItemPreview() {
    ComposeTheme {
        GitUserDetailCard(
            name = "longdt57",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            location = "San Francisco, CA"
        )
    }
}