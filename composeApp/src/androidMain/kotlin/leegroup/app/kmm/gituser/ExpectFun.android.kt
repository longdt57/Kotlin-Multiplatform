package leegroup.app.kmm.gituser

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import leegroup.app.kmm.gituser.ui.components.UserAvatar
import leegroup.app.kmm.gituser.ui.screens.main.gituser.components.GitUserListAppBar

@Composable
actual fun UserAvatar(modifier: Modifier, avatarUrl: String?) {
    UserAvatar(
        modifier = modifier,
        avatarUrl = avatarUrl
    )
}

@Composable
actual fun GitUserListAppBar() {
    GitUserListAppBar()
}

@Composable
actual fun stringRetry(): String {
    return stringResource(R.string.common_retry)
}

@Composable
actual fun stringOK(): String {
    return stringResource(R.string.common_ok)
}

@Composable
actual fun stringClose(): String {
    return stringResource(R.string.common_close)
}

@Composable
actual fun stringLoading(): String {
    return stringResource(R.string.loading)
}

@Composable
actual fun stringPopupErrorUnknownTitle(): String {
    return stringResource(R.string.popup_error_unknown_title)
}

@Composable
actual fun stringPopupErrorNoConnectionTitle(): String {
    return stringResource(R.string.popup_error_no_connection_title)
}

@Composable
actual fun stringPopupErrorTimeOutTitle(): String {
    return stringResource(R.string.popup_error_timeout_title)
}

@Composable
actual fun stringPopupErrorUnknownBody(): String {
    return stringResource(R.string.popup_error_unknown_body)
}

@Composable
actual fun stringPopupErrorNoConnectionBody(): String {
    return stringResource(R.string.popup_error_no_connection_body)
}

@Composable
actual fun stringPopupErrorTimeOutBody(): String {
    return stringResource(R.string.popup_error_timeout_body)
}
