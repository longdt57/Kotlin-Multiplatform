package leegroup.module.gituser.ui.models

import androidx.compose.runtime.Immutable
import leegroup.module.gituser.domain.models.GitUserModel

@Immutable
data class GitUserListUiModel(
    val users: List<GitUserModel> = emptyList(),
)
