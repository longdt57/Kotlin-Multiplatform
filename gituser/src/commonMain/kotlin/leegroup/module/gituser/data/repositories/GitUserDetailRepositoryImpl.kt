package leegroup.module.gituser.data.repositories

import leegroup.module.gituser.data.local.room.GitUserDetailDao
import leegroup.module.gituser.data.models.GitUserDetail
import leegroup.module.gituser.data.models.mapToDomain
import leegroup.module.gituser.data.remote.GitUserApiService
import leegroup.module.gituser.domain.models.GitUserDetailModel
import leegroup.module.gituser.domain.repositories.GitUserDetailRepository

internal class GitUserDetailRepositoryImpl(
    private val appService: GitUserApiService,
    private val userDao: GitUserDetailDao,
) : GitUserDetailRepository {

    override suspend fun getRemote(login: String): GitUserDetailModel {
        return appService.getGitUserDetail(login).let { userDetail ->
            saveToLocal(userDetail)
            mapToDomain(userDetail)
        }
    }

    override suspend fun getLocal(login: String): GitUserDetailModel? {
        return userDao.getUserDetail(login)?.let { mapToDomain(it) }
    }

    private suspend fun saveToLocal(user: GitUserDetail) {
        userDao.upsert(user)
    }

    private fun mapToDomain(user: GitUserDetail) = user.mapToDomain()
}