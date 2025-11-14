package leegroup.module.gituser.domain.usecases.gituser

import kotlinx.coroutines.flow.flow
import leegroup.module.gituser.domain.repositories.GitUserDetailRepository

class GetGitUserDetailLocalUseCase  constructor(
    private val repository: GitUserDetailRepository
) {

    operator fun invoke(login: String) = flow {
        repository.getLocal(login)?.let {
            emit(it)
        }
    }
}
