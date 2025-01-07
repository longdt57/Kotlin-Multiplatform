package leegroup.app.kmm.gituser

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Composable
 */
@Composable
expect fun UserAvatar(modifier: Modifier, avatarUrl: String?)

@Composable
expect fun GitUserListAppBar()

/**
 * String
 */
@Composable
expect fun stringRetry(): String

@Composable
expect fun stringPopupErrorUnknownTitle(): String

@Composable
expect fun stringLoading(): String

@Composable
expect fun stringOK(): String

@Composable
expect fun stringPopupErrorUnknownBody(): String

@Composable
expect fun stringClose(): String

@Composable
expect fun stringPopupErrorNoConnectionTitle(): String

@Composable
expect fun stringPopupErrorNoConnectionBody(): String

@Composable
expect fun stringPopupErrorTimeOutTitle(): String

@Composable
expect fun stringPopupErrorTimeOutBody(): String