package leegroup.app.kmm.gituser.domain.repositories

import leegroup.app.kmm.gituser.domain.models.GitUserDetailModel

interface GitUserDetailRepository {
    suspend fun getRemote(login: String): GitUserDetailModel
    suspend fun getLocal(login: String): GitUserDetailModel?
}