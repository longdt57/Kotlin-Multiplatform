package leegroup.module.gituser.domain.repositories

import leegroup.module.gituser.domain.models.GitUserDetailModel

interface GitUserDetailRepository {
    suspend fun getRemote(login: String): GitUserDetailModel
    suspend fun getLocal(login: String): GitUserDetailModel?
}