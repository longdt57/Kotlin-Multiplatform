package leegroup.app.kmm.gituser.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import leegroup.app.kmm.gituser.R
import leegroup.app.kmm.gituser.ui.theme.ComposeTheme

@Composable
fun UserAvatar(modifier: Modifier = Modifier, avatarUrl: String?) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray.copy(alpha = 0.5f)),
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(avatarUrl)
                .crossfade(true)
                .placeholder(R.drawable.im_avatar_placeholder) // Unified placeholder
                .error(R.drawable.im_avatar_placeholder) // Unified placeholder
                .fallback(R.drawable.im_avatar_placeholder)
                .build(),
            contentDescription = stringResource(id = R.string.user_avatar),
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserCircleAvatarPreview() {
    ComposeTheme {
        UserAvatar(
            modifier = Modifier.size(40.dp),
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
        )
    }
}
