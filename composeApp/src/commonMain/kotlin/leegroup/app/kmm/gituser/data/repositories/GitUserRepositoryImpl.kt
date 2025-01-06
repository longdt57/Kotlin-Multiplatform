package leegroup.app.kmm.gituser.data.repositories

import leegroup.app.kmm.gituser.data.extensions.transform
import leegroup.app.kmm.gituser.data.models.GitUser
import leegroup.app.kmm.gituser.data.models.mapToDomain
import leegroup.app.kmm.gituser.data.remote.ApiService
import leegroup.app.kmm.gituser.domain.models.GitUserModel
import leegroup.app.kmm.gituser.domain.repositories.GitUserRepository

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