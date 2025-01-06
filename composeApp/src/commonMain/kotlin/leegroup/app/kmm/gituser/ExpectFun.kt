package leegroup.app.kmm.gituser

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun stringRetry(): String

@Composable
expect fun stringLoading(): String

@Composable
expect fun UserAvatar(modifier: Modifier, avatarUrl: String?)
