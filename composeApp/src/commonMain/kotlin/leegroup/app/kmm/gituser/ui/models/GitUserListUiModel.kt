package leegroup.app.kmm.gituser.ui.models

import androidx.compose.runtime.Immutable
import leegroup.app.kmm.gituser.domain.models.GitUserModel

@Immutable
data class GitUserListUiModel(
    val users: List<GitUserModel> = emptyList(),
)
