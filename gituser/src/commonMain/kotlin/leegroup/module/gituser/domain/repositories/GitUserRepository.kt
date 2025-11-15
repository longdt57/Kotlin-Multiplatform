package leegroup.module.gituser.domain.repositories

import leegroup.module.gituser.domain.models.GitUserModel

interface GitUserRepository {
    suspend fun getRemote(since: Long, perPage: Int): List<GitUserModel>
    suspend fun getLocal(since: Long, perPage: Int): List<GitUserModel>
}