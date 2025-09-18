package leegroup.app.kmm.gituser.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import gituserkmm.composeapp.generated.resources.Res
import gituserkmm.composeapp.generated.resources.im_avatar_placeholder
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import leegroup.module.designsystem.theme.ComposeTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun UserAvatar(modifier: Modifier = Modifier, avatarUrl: String?) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray.copy(alpha = 0.5f)),
    ) {
        KamelImage(
            resource = asyncPainterResource(avatarUrl.orEmpty()),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            onLoading = {
                CircularProgressIndicator(Modifier.padding(16.dp))
            },
            onFailure = {
                Image(
                    painter = painterResource(Res.drawable.im_avatar_placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
        )
    }
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
