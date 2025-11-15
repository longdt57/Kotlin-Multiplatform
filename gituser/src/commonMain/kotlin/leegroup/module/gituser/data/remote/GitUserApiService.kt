package leegroup.module.gituser.data.remote

import leegroup.module.gituser.data.models.GitUser
import leegroup.module.gituser.data.models.GitUserDetail

internal interface GitUserApiService {

    suspend fun getGitUser(
        since: Long,
        perPage: Int,
    ): List<GitUser>

    suspend fun getGitUserDetail(
        login: String,
    ): GitUserDetail
}
