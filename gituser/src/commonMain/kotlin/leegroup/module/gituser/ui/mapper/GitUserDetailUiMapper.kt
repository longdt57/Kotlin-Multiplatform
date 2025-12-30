package leegroup.module.gituser.ui.mapper

import gituserkmm.core.designsystem.generated.resources.Res
import gituserkmm.core.designsystem.generated.resources.not_set
import leegroup.module.gituser.domain.models.GitUserDetailModel
import leegroup.module.gituser.ui.models.GitUserDetailUiModel
import leegroup.module.gituser.ui.mapper.util.FollowerFormatter
import org.jetbrains.compose.resources.getString

internal interface GitUserDetailUiMapper {
    suspend fun mapToUiModel(
        oldUiModel: GitUserDetailUiModel,
        model: GitUserDetailModel
    ): GitUserDetailUiModel
}

internal class GitUserDetailUiMapperImpl : GitUserDetailUiMapper {

    override suspend fun mapToUiModel(
        oldUiModel: GitUserDetailUiModel,
        model: GitUserDetailModel
    ): GitUserDetailUiModel {
        return oldUiModel.copy(
            login = model.login,
            name = model.name ?: model.login,
            avatarUrl = model.avatarUrl.orEmpty(),
            blog = model.blog.orEmpty(),
            location = model.location.takeUnless { it.isNullOrBlank() }
                ?: getString(Res.string.not_set),
            followers = FollowerFormatter.formatLargeNumber(model.followers),
            following = FollowerFormatter.formatLargeNumber(model.following),
        )
    }
}