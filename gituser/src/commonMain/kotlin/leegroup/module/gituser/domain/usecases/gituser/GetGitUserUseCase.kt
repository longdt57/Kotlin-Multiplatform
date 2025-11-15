package leegroup.module.gituser.domain.usecases.gituser

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import leegroup.module.gituser.domain.models.GitUserModel
import leegroup.module.gituser.domain.repositories.GitUserRepository

class GetGitUserUseCase(
    private val repository: GitUserRepository
) {

    operator fun invoke(since: Long, perPage: Int): Flow<List<GitUserModel>> {
        return flow {
            val localData = repository.getLocal(since, perPage)
            if (localData.isEmpty()) {
                emit(repository.getRemote(since, perPage))
            } else {
                emit(localData)
            }
        }
    }
}