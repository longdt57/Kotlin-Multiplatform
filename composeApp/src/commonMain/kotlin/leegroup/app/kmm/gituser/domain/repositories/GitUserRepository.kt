package leegroup.app.kmm.gituser.domain.repositories

import leegroup.app.kmm.gituser.domain.models.GitUserModel

interface GitUserRepository {
    suspend fun getRemote(since: Int, perPage: Int): List<GitUserModel>
    suspend fun getLocal(since: Int, perPage: Int): List<GitUserModel>
}