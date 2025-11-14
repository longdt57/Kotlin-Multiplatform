package leegroup.module.gituser.domain.usecases.gituser

import kotlinx.coroutines.flow.flow
import leegroup.module.gituser.domain.repositories.GitUserDetailRepository

class GetGitUserDetailRemoteUseCase constructor(
    private val repository: GitUserDetailRepository
) {

    operator fun invoke(login: String) = flow {
        emit(repository.getRemote(login))
    }
}
