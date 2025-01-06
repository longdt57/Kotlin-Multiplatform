package leegroup.app.kmm.gituser

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import leegroup.app.kmm.gituser.support.extensions.formatAndOpenUrl
import leegroup.app.kmm.gituser.ui.components.UserAvatar

@Composable
actual fun UserAvatar(modifier: Modifier, avatarUrl: String?) {
    UserAvatar(
        modifier = modifier,
        avatarUrl = avatarUrl
    )
}

@Composable
actual fun stringRetry(): String {
    return stringResource(R.string.common_retry)
}

@Composable
actual fun stringLoading(): String {
    return stringResource(R.string.loading)
}