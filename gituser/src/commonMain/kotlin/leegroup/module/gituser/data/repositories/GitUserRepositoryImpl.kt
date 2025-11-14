package leegroup.module.gituser.data.repositories

import leegroup.module.gituser.data.extensions.transform
import leegroup.module.gituser.data.models.GitUser
import leegroup.module.gituser.data.models.mapToDomain
import leegroup.module.gituser.data.remote.ApiService
import leegroup.module.gituser.domain.models.GitUserModel
import leegroup.module.gituser.domain.repositories.GitUserRepository

class GitUserRepositoryImpl(
    private val appService: ApiService,
) : GitUserRepository {

    override suspend fun getRemote(since: Int, perPage: Int) = transform {
        val users = appService.getGitUser(since = since, perPage = perPage)
        saveToLocal(users)
        mapToDomain(users)
    }

    override suspend fun getLocal(since: Int, perPage: Int): List<GitUserModel> {
        return emptyList()
    }

    private suspend fun saveToLocal(users: List<GitUser>) {
        // Todo Implement
    }

    private fun mapToDomain(users: List<GitUser>): List<GitUserModel> {
        return users.mapToDomain()
    }
}