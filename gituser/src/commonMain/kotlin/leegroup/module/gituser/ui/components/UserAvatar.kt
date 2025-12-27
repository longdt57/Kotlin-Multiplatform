package leegroup.module.gituser.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import leegroup.module.designsystem.theme.ComposeTheme
import leegroup.module.gituser.icon.GitUserIcons
import leegroup.module.gituser.icon.ImAvatarPlaceholder
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun UserAvatar(modifier: Modifier = Modifier, avatarUrl: String?) {
    AsyncImage(
        modifier = modifier
            .fillMaxSize()
            .clip(CircleShape),
        model = avatarUrl,

        error = rememberVectorPainter(GitUserIcons.ImAvatarPlaceholder),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Preview
@Composable
private fun UserCircleAvatarPreview() {
    ComposeTheme {
        UserAvatar(
            modifier = Modifier.size(40.dp),
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
        )
    }
}
