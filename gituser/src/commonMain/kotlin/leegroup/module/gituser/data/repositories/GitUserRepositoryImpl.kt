package leegroup.module.gituser.data.repositories

import leegroup.module.data.getRoomDatabase
import leegroup.module.gituser.data.extensions.transform
import leegroup.module.gituser.data.local.room.GitUserDao
import leegroup.module.gituser.data.local.room.GitUserDatabase
import leegroup.module.gituser.data.models.GitUser
import leegroup.module.gituser.data.models.mapToDomain
import leegroup.module.gituser.data.remote.GitUserApiService
import leegroup.module.gituser.domain.models.GitUserModel
import leegroup.module.gituser.domain.repositories.GitUserRepository

internal class GitUserRepositoryImpl(
    private val appService: GitUserApiService,
    private val userDao: GitUserDao,
) : GitUserRepository {

    override suspend fun getRemote(since: Int, perPage: Int) = transform {
        val users = appService.getGitUser(since = since, perPage = perPage)
        saveToLocal(users)
        mapToDomain(users)
    }

    override suspend fun getLocal(since: Int, perPage: Int): List<GitUserModel> {
        return userDao
            .getUsers(since = since, perPage = perPage)
            .let { mapToDomain(it) }
    }

    private suspend fun saveToLocal(users: List<GitUser>) {
        userDao.upsert(users)
    }

    private fun mapToDomain(users: List<GitUser>): List<GitUserModel> {
        return users.mapToDomain()
    }
}